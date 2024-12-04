package com.epoch.controller;


import com.epoch.model.vo.Result;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * 发布评论
     * @return 结果
     */
    @PostMapping("publishComment")
    public Result publishComment() {
        return null;
    }


    /**
     * 获取我的评论记录
     * @return 结果
     */
    @GetMapping("getMyComments")
    public Result getMyComments() {
        return null;
    }


    /**
     * 删除评论
     * @return 结果
     */
    @DeleteMapping("deleteComment")
    public Result deleteComment() {
        return null;
    }


    /**
     * 获取某个帖子下面的评论
     * @return 结果
     */
    @GetMapping("/getComments")
    public Result getComments() {
        return null;
    }




}
