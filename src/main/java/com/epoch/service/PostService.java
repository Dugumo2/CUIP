package com.epoch.service;

import com.epoch.model.dto.UploadPost;
import com.epoch.model.po.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epoch.model.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 帖子 服务类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
public interface PostService extends IService<Post> {

    Object uploadPostImg(MultipartFile file, String objectName) throws Exception;

    void submitPost(UploadPost uploadPost);

    Object getPostScore(String topicId);

    void submitPostScore(String topicId, Integer score);

    Object getTopicScore(String topicId);

    Result getPostInfoById(String postId);

    Result getCarouselInfo(String postId);
}
