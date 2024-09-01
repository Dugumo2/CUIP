package com.epoch.model.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户对帖子的点赞或者点踩表
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPostLikeDislike implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String userPostLikeDislike;

    private String userId;

    private String postId;

    /**
     * 1赞，0踩
     */
    private Integer upOrDown;


}
