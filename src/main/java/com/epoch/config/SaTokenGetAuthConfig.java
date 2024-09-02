package com.epoch.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.epoch.model.po.User;
import com.epoch.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("All")
public class SaTokenGetAuthConfig implements StpInterface {
    @Resource
    UserService userService;

    @Override
    public List<String> getPermissionList(Object loginIdObj, String loginType) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String loginId = (String) loginIdObj;
        queryWrapper.eq(User::getUserId,loginId);
        User one = userService.getOne(queryWrapper);
        ArrayList<String> list = new ArrayList<>();
        if (one == null) {

        } else if (one.getIsAdmin()) {
            list.add("admin.*");
        } else {
            list.add("user.*");
        }

        return list;
    }

    @Override
    public List<String> getRoleList(Object loginIdObj, String loginType) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String loginId = (String) loginIdObj;
        queryWrapper.eq(User::getUserId,loginId);
        User one = userService.getOne(queryWrapper);
        ArrayList<String> list = new ArrayList<>();
        if (one == null) {

        } else if (one.getIsAdmin()) {
            list.add("admin");
        } else {
            list.add("user");
        }

        return list;
    }
}
