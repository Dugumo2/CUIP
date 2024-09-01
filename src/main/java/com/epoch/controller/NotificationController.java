package com.epoch.controller;


import com.epoch.model.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通知 前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {
    /**
     * 获取我的通知
     * @return 结果
     */
    @GetMapping("getNotifications")
    public Result getNotifications() {
        return null;
    }

}
