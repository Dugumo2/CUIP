package com.epoch;

import cn.dev33.satoken.secure.BCrypt;
import com.epoch.controller.UserController;
import com.epoch.util.BaiduAiUtil;
import com.epoch.util.MailUtil;
import com.epoch.util.MinioUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CuipuApplicationTests {
    @Resource
    UserController userController;
    @Resource
    MailUtil mailUtil;
    @Resource
    BaiduAiUtil baiduAiUtil;

    @Resource
    private MinioUtil minioUtil;


    @Test
    void contextLoads() {
        System.out.println(BCrypt.hashpw("1234qwer", BCrypt.gensalt(12)));
    }


    @Test
    void testLoginOut() {
        userController.loginOut();
    }

    @Test
    void testSendMail() {
        mailUtil.sendMail("2352486250@qq.com","标题","内容");
    }

    @Test
    void testTextCensor() throws JSONException {  // 添加这个新的测试方法
        JSONObject res = baiduAiUtil.textCensor("我要玩原神");
        System.out.println(res.toString(2));
    }

    @Test
    void testImageCensor() {
        // 参数为url
        JSONObject res = baiduAiUtil.imageCensor("https://p9.itc.cn/q_70/images03/20211111/70efddcdaab04d3496bf44f865390f10.jpeg");
        System.out.println(res.toString());
    }

    @Test
    void testMinio1() throws Exception{
//        System.out.println(minioUtil.bucketExists("epochbucket"));
        System.out.println(minioUtil.getAllBucket());
    }
//
//    /*
//    测一下文件上传
//     */
//    @Test
//    void testMinio2() throws Exception{
//        // 读取本地文件作为测试数据
//        String filePath = "D:\\离别.png";
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        MultipartFile multipartFile = new MockMultipartFile("file.txt", "file.txt", "text/plain", fileInputStream);
//        System.out.println(multipartFile);
//
//        // 调用upload方法
//        String result = minioUtil.upload(multipartFile);
//
//        // 断言结果不为空
//        assertNotNull(result);
//    }

}
