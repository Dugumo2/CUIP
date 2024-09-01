package com.epoch.util;

import com.epoch.model.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MailUtil {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    public Boolean sendMail(String email,
                              String title,
                              String content){

        //生存发送存储qq验证码
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setText(content);
            mailMessage.setTo(email);
            mailMessage.setFrom(from);
            mailMessage.setSubject(title);
            mailSender.send(mailMessage);
        }catch (MailSendException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
