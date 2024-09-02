package com.epoch.service;

import com.epoch.model.dto.EpassUserDO;
import com.epoch.model.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.epoch.model.vo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
public interface UserService extends IService<User> {

    Result login(String email, String pwd);

    Result loginOut();
    //Epass登陆
    Object getEpass(String sauce);
    // 新增获取用户全部信息的方法
    Object getUserInfo(String userId);
    // 获取用户基本信息
    Object getUserBasicInfo(String userId);
}
