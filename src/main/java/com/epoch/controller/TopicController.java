package com.epoch.controller;


import com.epoch.model.vo.Result;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
=======
import com.epoch.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
<<<<<<< HEAD
=======
    @Resource
    TopicService topicService;

>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e
    /**
     * 获取话题列表(包括获取我收藏的话题列表)
     * @return 结果
     */
<<<<<<< HEAD
    @GetMapping("getTopics")
=======
    @GetMapping("/getTopics")
>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e
    public Result getTopics() {
        return null;
    }


    /**
     * 获取话题详情
     * @return 结果
     */
<<<<<<< HEAD
    @GetMapping("getTopicInfo")
    public Result getTopicInfo() {
        return null;
=======
    @GetMapping("/getTopicInfo")
    public Result getTopicInfo(@RequestParam String topicId) {
        return topicService.getTopicInfoById(topicId);
>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e
    }


    /**
     * 收藏或者取消收藏话题
     * @return 结果
     */
<<<<<<< HEAD
    @PutMapping("changeTopicCollectionStatus")
=======
    @PutMapping("/changeTopicCollectionStatus")
>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e
    public Result changeTopicCollectionStatus() {
        return null;
    }


    /**
     * 对话题进行打分
     * @return 结果
     */
<<<<<<< HEAD
    @PutMapping("scoringTopic")
=======
    @PutMapping("/scoringTopic")
>>>>>>> 8b02c0e4c414b6c69aa92d05f37f57e2bb0ffa4e
    public Result scoringTopic() {
        return null;
    }
}
