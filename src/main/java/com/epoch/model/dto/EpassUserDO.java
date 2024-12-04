package com.epoch.model.dto;

import lombok.Data;

@Data
public class EpassUserDO {

    private Integer userId;
    private String username;
    private String displayName;
    private String email;
    private Long issueDate;

}