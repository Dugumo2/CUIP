package com.epoch.controller;

import com.epoch.model.dto.EpassUserDO;
import com.epoch.model.po.Post;
import com.epoch.model.po.User;
import com.epoch.model.vo.Result;
import com.epoch.service.UserService;
import com.epoch.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;
    /**
     * 登陆
     * @param email 邮箱
     * @param pwd 密码
     * @return 登录情况
     */
    @GetMapping("/login")
    public Result login(@RequestParam String email,@RequestParam String pwd) {
        return userService.login(email,pwd);
    }
    /**
     * 登出
     * @return 登出情况
     */
    @GetMapping("/loginOut")
    public Result loginOut() {
        return userService.loginOut();
    }


    /**
     * 注册
     * @return 结果
     */
    @GetMapping("/register")
    public Result register() {
        return null;
    }

    /**
     * 找回密码
     * @return 结果
     */
    @GetMapping("/retrievePwd")
    public Result retrievePwd() {
        return null;
    }

    /**
     * 获取用户信息
     * @return 结果
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam String userId) {
        return Result.ok(userService.getUserInfo(userId));
    }

    /**
     * 修改用户信息
     * @return 结果
     */
    @PostMapping("/modifyUserInfo")
    public Result modifyUserInfo() {
        return null;
    }

    @GetMapping("/EpassLogin")
    public Result EpassLogin(@RequestParam("sauce")String sauce) {
        return Result.ok(userService.getEpass(sauce));
    }

    @PostMapping("/getBasicUserInfo")
    public Result getUserBasicInfo(@RequestParam String userId){
        return Result.ok(userService.getUserBasicInfo(userId));
    }


}
