package com.epoch.model.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String userId;

    private String studentId;

    /**
     * 1男，0女
     */
    private Integer gender;

    private String nickname;

    private String email;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private String avatarUrl;

    /**
     * 简介
     */
    private String bio;

    private Integer status;

    private Boolean isOnline;

    private Boolean isDeleted;

    private Boolean isAdmin;

    /**
     * 密码
     */
    private String password;

    private Integer epochId;


}
