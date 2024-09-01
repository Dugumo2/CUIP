package com.epoch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.epoch.model.po.Topic;
import com.epoch.mapper.TopicMapper;
import com.epoch.model.vo.Result;
import com.epoch.model.vo.TopicVo;
import com.epoch.service.PostService;
import com.epoch.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    PostService postService;

    @Override
    public Result getTopicInfoById(String topicId) {
        Topic topic = getById(topicId);
        Integer count = postService.query().eq("topicId", topicId).count();
        TopicVo topicVo = BeanUtil.copyProperties(topic, TopicVo.class);
        topicVo.setPostCounts(count);
        return Result.ok(topicVo);
    }
}
