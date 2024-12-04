package com.epoch.controller;

import com.epoch.model.vo.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 新增标签
     * @return 结果
     */
    @PostMapping("addLabel")
    public Result addLabel() {
        return null;
    }

    /**
     * 删除标签
     * @return 结果
     */
    @DeleteMapping("deleteLabel")
    public Result deleteLabel() {
        return null;
    }

    /**
     * 获取标签列表
     * 直接调用其他同学的实现
     * @return 结果
     */
    @GetMapping("getLabels")
    public Result getLabels() {
        return null;
    }

    /**
     * 获取话题列表
     * 直接调用其他同学的实现
     * @return 结果
     */
    @GetMapping("getTopics")
    public Result getTopics() {
        return null;
    }

    /**
     * 发布话题
     * @return 结果
     */
    @PostMapping("publishTopic")
    public Result publishTopic() {
        return null;
    }

    /**
     * 删除话题
     * @return 结果
     */
    @DeleteMapping("deleteTopic")
    public Result deleteTopic() {
        return null;
    }

    /**
     * 修改话题
     * @return 结果
     */
    @PutMapping("modifyTopic")
    public Result modifyTopic() {
        return null;
    }

    /**
     * 删除帖子
     * @return 结果
     */
    @DeleteMapping("deletePost")
    public Result deletePost() {
        return null;
    }

    /**
     * 删除评论
     * @return 结果
     */
    @DeleteMapping("/deleteComment")
    public Result deleteComment() {
        return null;
    }

    /**
     * 获取话题详情
     * 直接调用其他同学的实现
     * @return 结果
     */
    @GetMapping("getTopicInfo")
    public Result getTopicInfo() {
        return null;
    }

    /**
     * 获取帖子详情
     * 直接调用其他同学的实现
     * @return 结果
     */
    @GetMapping("/getPostInfo")
    public Result getPostInfo() {return null;}


    /**
     * 获取用户列表
     * @return 结果
     */
    @GetMapping("getUsers")
    public Result getUsers() {
        return null;
    }

    /**
     * 封禁和解封用户
     * @return 结果
     */
    @PutMapping("banOrUnblockUser")
    public Result banOrUnblockUser() {
        return null;
    }
}
