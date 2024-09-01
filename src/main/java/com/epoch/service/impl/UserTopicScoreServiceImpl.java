package com.epoch.service.impl;

import com.epoch.model.po.UserTopicScore;
import com.epoch.mapper.UserTopicScoreMapper;
import com.epoch.service.UserTopicScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户对话题评分表 服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class UserTopicScoreServiceImpl extends ServiceImpl<UserTopicScoreMapper, UserTopicScore> implements UserTopicScoreService {

}
