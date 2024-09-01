package com.epoch.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.epoch.model.dto.BasicUser;
import com.epoch.model.dto.EpassUserDO;
import com.epoch.model.po.User;
import com.epoch.mapper.UserMapper;
import com.epoch.model.vo.Result;
import com.epoch.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final int GCM_TAG_LENGTH = 16;
    private static final int GCM_IV_LENGTH = 12;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result login(String email, String pwd) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email)
                .eq(User::getIsAdmin,false);
        User one = getOne(queryWrapper);
        if (one == null) {
            return Result.fail("用户不存在");
        }

        String password = one.getPassword();
        boolean b = BCrypt.checkpw(pwd, password);
        if (b) {
            StpUtil.login(one.getUserId());
            return Result.ok(StpUtil.getTokenInfo());
        } else {
            return Result.fail("邮箱或者密码错误");
        }
    }

    @Override
    public Result loginOut() {
        StpUtil.logout();
        return Result.ok();
    }

    @Override
    public Result getEpass(String sauce){
        try {
            EpassUserDO epassUserDO = decrypt(sauce);
            // 获取当前时间的 Instant 对象
            Instant now = Instant.now();
            // 将当前时间转换为 ZonedDateTime 对象
            ZonedDateTime currentDateTime = now.atZone(ZoneId.systemDefault());
            // 计算5秒之前的时间
            ZonedDateTime fiveSecondsAgo = currentDateTime.minusSeconds(5);
            // 将签名的签发时间戳转换为 ZonedDateTime 对象
            ZonedDateTime signatureIssueDateTime = ZonedDateTime.ofInstant(
                    Instant.ofEpochSecond(epassUserDO.getIssueDate()),
                    ZoneId.systemDefault()
            );
            // 比较签发时间和5秒前的时间
            if (signatureIssueDateTime.isBefore(fiveSecondsAgo)) {
                // 签发时间早于5秒前，校验失败
                return Result.fail("签发时间早于5秒前，校验失败");
            } else {

                    User user = userMapper.selectUserByEpochId(epassUserDO.getUserId());
                    if (user != null) {
                        StpUtil.login(user.getUserId());
                        log.info("登陆成功");
                        return Result.ok(StpUtil.getTokenInfo());
                    } else {
                        return Result.fail("未找到对应的epochId");
                    }
                }
            }catch (Exception e) {
            return Result.fail("Error processing sauce");
        }
    }
    public static EpassUserDO decrypt(String sauce) throws Exception {
        // Base64URL 解码
        byte[] sauceBytes = Base64.getUrlDecoder().decode(sauce);

        // 前 12 字节为IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        System.arraycopy(sauceBytes, 0, iv, 0, GCM_IV_LENGTH);

        // 剩余部分为密文（包含认证标签）
        int ciphertextLength = sauceBytes.length - GCM_IV_LENGTH;
        byte[] ciphertextWithTag = new byte[ciphertextLength];
        System.arraycopy(sauceBytes, GCM_IV_LENGTH, ciphertextWithTag, 0, ciphertextLength);

        // 创建 AES 密钥
        String APP_SECRET = "00000000000000000000000000000000";
        SecretKeySpec keySpec = new SecretKeySpec(APP_SECRET.getBytes(StandardCharsets.UTF_8), "AES");

        // 设置 GCM 参数
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        byte[] decryptedText = null;


        // 初始化解密 Cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // 解密（密文和认证标签都在 ciphertextWithTag 中）
        decryptedText = cipher.doFinal(ciphertextWithTag);

        // 返回解密后的字符串转化为EpassUserDo对象
        return new ObjectMapper().readValue(new String(decryptedText, StandardCharsets.UTF_8), EpassUserDO.class);
    }

    @Override
    public Result getUserInfo(String userId) {
            // 检查用户是否已经登录
            if (!StpUtil.isLogin(userId)) {
                return Result.fail("用户未登录或登录已过期，请重新登录");
            }
            // 根据userId获取用户信息
            User user = userMapper.getAllUserInfo(userId);
            if (user == null) {
                return Result.fail("用户不存在");
            }
            return Result.ok(user);
    }

    @Override
    public Result getUserBasicInfo(String userId) {
        // 调用Mapper方法获取用户基本信息
        BasicUser user = userMapper.getBasicUserInfo(userId);
        return Result.ok(user);
    }
}
