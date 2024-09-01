package com.epoch.model.po;

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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String commentId;

    private String postId;

    /**
     * 为空时，表明回复的是一级评论，不为空时表明回复的时二级评论
     */
    private String toUserId;

    /**
     * 有的话是1级评论，没有的话是二级评论
     */
    private String parentId;

    private String content;

    private String senderId;

    private Boolean deleted;


}
