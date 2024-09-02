package com.epoch.service.impl;

import com.epoch.model.po.UserPostLikeDislike;
import com.epoch.mapper.UserPostLikeDislikeMapper;
import com.epoch.service.UserPostLikeDislikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户对帖子的点赞或者点踩表 服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class UserPostLikeDislikeServiceImpl extends ServiceImpl<UserPostLikeDislikeMapper, UserPostLikeDislike> implements UserPostLikeDislikeService {

}
