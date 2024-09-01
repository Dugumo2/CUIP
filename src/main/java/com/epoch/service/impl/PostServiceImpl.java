package com.epoch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.epoch.model.dto.UploadPost;
import com.epoch.model.po.Post;
import com.epoch.mapper.PostMapper;
import com.epoch.model.po.User;
import com.epoch.model.po.UserTopicScore;
import com.epoch.model.vo.PostVo;
import com.epoch.model.vo.Result;
import com.epoch.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epoch.service.UserService;
import com.epoch.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.epoch.util.SystemConstants.DEFAULT_SCORE;

/**
 * <p>
 * 帖子 服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    UserService userService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private MinioUtil minioUtil;

    @Value("${minio.bucketName}")
    private String bucket;
    /**
     * 上传文件到minio中，返回img_urls
     *
     * @param file
     * @param objectName
     * @return
     */
    @Override
    public Object uploadPostImg(MultipartFile file, String objectName) throws Exception {
        if (file.isEmpty()) {
            return Result.fail("上传的文件不能为空！");
        }
            String finalName = objectName + "." + getFileExtension(file);
            String urls = minioUtil.uploadFile(file, bucket, finalName);
            log.info("上传文件成功，objectName：{}", finalName);
            return Result.ok(urls);
    }




    @Override
    public void submitPost(UploadPost uploadPost) {
        //获取用户信息
        String userId = "2ccda13d-3251-4bf7-9fe8-456ab8828d92";

        //获取帖子信息
        Post post = new Post();
        post.setUserId(userId)
               .setTitle(uploadPost.getTitle())
               .setContent(uploadPost.getContent())
               .setImgUrls(uploadPost.getImgUrls())
                .setTopicId(uploadPost.getTopicId())
                .setLikeCount(0).setDislikeCount(0)
                .setIsDeleted(false).setStatus(0)
                .setPublishTime(LocalDateTime.now()).setModifyTime(LocalDateTime.now());

        //将帖子信息保存到数据库
//        postMapper.submitPost(post);
        save(post);

    }

    /**
     * 获取用户对该topic的评分
     * 有就返回评分值，没有返回-1
     * @param topicId
     * @return
     */
    @Override
    public Object getPostScore(String topicId) {
        //获取用户信息
        String userId = "2ccda13d-3251-4bf7-9fe8-456ab8828d92";

        //获取该用户对该topic评分
        Float score = postMapper.getPostScore(userId, topicId);

        if (Objects.nonNull(score)) {
            return Result.ok(score);
        }

        return Result.ok(DEFAULT_SCORE);
    }

    /**
     * 用户对topic进行评分
     * @param topicId
     * @param score
     */
    @Override
    @Transactional
    public void submitPostScore(String topicId, Integer score) {
        //获取用户信息
        String userId = "2ccda13d-3251-4bf7-9fe8-456ab8828d92";

        //判断该用户是否对该topic进行过评分
        if(!postMapper.isUserTopicScoreExists(userId, topicId)){
            //第一次发布评分，创建UserTopicScore记录
            UserTopicScore userTopicScore = new UserTopicScore();
            userTopicScore.setUserId(userId).setTopicId(topicId)
                    .setScore(score).setUserTopicScoreId(UUID.randomUUID().toString());
            postMapper.submitPostScore(userTopicScore);
        }
        else {
            //修改评分，更新该用户对该topic的评分
            postMapper.updatePostScore(userId, topicId, score);
        }

        //获取此时该topic的平均分
        Double scoreAvg = Double.valueOf(postMapper.getTopicScoreAvg(topicId));
        System.out.println(scoreAvg);

        //更新该topic的评分
        postMapper.updateTopicScore(topicId, scoreAvg);

    }

    @Override
    public Object getTopicScore(String topicId) {
        //获取该topic的平均分
        Float scoreAvg = postMapper.getTopicScoreAvg(topicId);

        if (Objects.nonNull(scoreAvg)) {
            return Result.ok(scoreAvg);
        }
        return Result.ok(DEFAULT_SCORE);
    }

    @Override
    public Result getPostInfoById(String postId) {
        Post post = getById(postId);
        String userId = post.getUserId();
        User user = userService.getById(userId);
        String avatarUrl = user.getAvatarUrl();
        String nickname = user.getNickname();
        PostVo postVo = BeanUtil.copyProperties(post, PostVo.class);
        postVo.setAvatarUrl(avatarUrl);
        postVo.setNickname(nickname);
        return Result.ok(postVo);
    }

    @Override
    public Result getCarouselInfo(String postId) {
        //获取帖子信息
        Post post = getById(postId);

        return Result.ok();
    }

    /**
     * 获取文件后缀（不带.)
     * @param file
     * @return
     */
    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }
}
