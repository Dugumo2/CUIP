package com.epoch.controller;


import com.epoch.model.dto.UploadPost;
import com.epoch.model.vo.Result;
import com.epoch.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * <p>
 * 帖子 前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/post")
public class PostController {


    @Autowired
    private PostService postService;


    /**
     * 获取我的帖子
     * @return 结果
     */
    @GetMapping("/getMyPosts")
    public Result getMyPost() {
        return null;
    }


    /**
     * 获取某个话题下的帖子
     * @return 结果
     */
    @GetMapping("/getPosts")
    public Result getPosts() {
        return null;
    }

    /**
     * 获取帖子详情
     * @return 结果
     */
    @GetMapping("/getPostInfo")
    public Result getPostInfo() {return null;}

    /**
     * 在某个话题下发布帖子
     * @return 结果
     */
    @PostMapping("/publishPost")
    public Result publishPost() {
        return null;
    }

    /**
     * 删除某个帖子
     * @return 结果
     */
    @DeleteMapping("deletePost")
    public Result deletePost() {
        return null;
    }

    /**
     * 对帖子点赞或者点踩
     * @return 结果
     */
    @GetMapping("likeOrDislikePost")
    public Result likeOrDislikePost() {
        return null;
    }

    /**
     * 上传帖子图片
     * @param file
     * @return
     */
    @PostMapping("/uploadPostImg")
    public Result uploadPostImg(MultipartFile file) throws Exception {
        return Result.ok(postService.uploadPostImg(file));
    }

    /**
     * 发布帖子
     * @param uploadPost
     * @return
     */
    @PostMapping("/submitPost")
    public Result submitPost(@RequestBody UploadPost uploadPost){
        if(uploadPost.getTopicId() == null || uploadPost.getContent() == null || uploadPost.getTitle() == null){
            return Result.fail("请完善帖子信息！");
        }

        postService.submitPost(uploadPost);
        return Result.ok("发布成功");
    }

    /**
     * 获取某个话题的评分
     * @param topicId
     * @return
     */
    @GetMapping("/getTopicScore/{topicId}")
    public Result getTopicScore(@PathVariable String topicId){
        return Result.ok(postService.getTopicScore(topicId));
    }


    /**
     * 用户是否进行了评分,传入topic_id、如果有就返回评分值，没有返回-1
     * @param topicId
     * @return
     */
    @GetMapping("/getScore/{topicId}")
    public Result getPostScore(@PathVariable String topicId){
        return Result.ok(postService.getPostScore(topicId));
    }

    /**
     * 用户提交评分
     * @param topicId
     * @param score
     * @return
     */
    @PostMapping("/submitPostScore/{topicId}")
    public Result submitPostScore(@PathVariable String topicId,@RequestParam Integer score){
        if(score == null){
            return Result.fail("请输入评分!");
        }

        postService.submitPostScore(topicId,score);
        return Result.ok("提交评分成功");
    }


}
