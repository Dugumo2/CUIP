package com.epoch.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class Base64URLDecoder {

    public static byte[] decode(String sauce) {
        // 将Base64URL编码转换为标准的Base64编码
        String standardBase64 = sauce.replace('-', '+').replace('_', '/').replace(",", "");

        log.info("Standard Base64: {}", standardBase64);
        // 进行Base64解码
        return Base64.getDecoder().decode(standardBase64);
    }
}
