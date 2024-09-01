package com.epoch.model.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 通知
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String notificationId;

    private String userId;

    private LocalDateTime sendTime;

    private String content;

    private String title;

    private String relatedId;

    /**
     * 1对帖子，2对评论
     */
    private Integer relatedType;


}
