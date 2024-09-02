package com.epoch.service;

import com.epoch.model.po.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epoch.model.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
public interface TopicService extends IService<Topic> {

    Result getTopicInfoById(String topicId);
}
