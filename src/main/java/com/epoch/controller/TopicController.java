package com.epoch.controller;


import com.epoch.model.vo.Result;
import com.epoch.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    TopicService topicService;

    /**
     * 获取话题列表(包括获取我收藏的话题列表)
     * @return 结果
     */
    @GetMapping("/getTopics")
    public Result getTopics() {
        return null;
    }


    /**
     * 获取话题详情
     * @return 结果
     */
    @GetMapping("/getTopicInfo")
    public Result getTopicInfo(@RequestParam String topicId) {
        return topicService.getTopicInfoById(topicId);
    }


    /**
     * 收藏或者取消收藏话题
     * @return 结果
     */
    @PutMapping("/changeTopicCollectionStatus")
    public Result changeTopicCollectionStatus() {
        return null;
    }


    /**
     * 对话题进行打分
     * @return 结果
     */
    @PutMapping("/scoringTopic")
    public Result scoringTopic() {
        return null;
    }
}
