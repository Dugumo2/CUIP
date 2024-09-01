package com.epoch.mapper;

import com.epoch.model.po.UserPostLikeDislike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户对帖子的点赞或者点踩表 Mapper 接口
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Mapper
public interface UserPostLikeDislikeMapper extends BaseMapper<UserPostLikeDislike> {

}
