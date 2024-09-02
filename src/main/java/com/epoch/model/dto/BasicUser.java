package com.epoch.model.dto;

import lombok.Data;

@Data
public class BasicUser {

    private String userId;
    private String nickname;
    private String avatarUrl;
    /**
     * 简介
     */
    private String bio;
}
