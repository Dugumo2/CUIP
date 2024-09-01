package com.epoch.model.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class EpassUserDO {
    @Getter
    private Integer userId;
    private String username;
    private String displayName;
    private String email;
    private Long issueDate;

}