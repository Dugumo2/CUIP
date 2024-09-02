package com.epoch.mapper;

import com.epoch.model.dto.BasicUser;
import com.epoch.model.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据实验室id获取用户
    User selectUserByEpochId(@Param("epochId") Integer epochId);
    //获取用户全部信息
    User getAllUserInfo(@Param("userId") String userId);
    //获取用户基本信息
    @Select("select * from user where user_id = #{userId}")
    BasicUser getBasicUserInfo(@Param("userId") String userId);
}
