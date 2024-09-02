package com.epoch.model.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    @TableId

    private String postId;

    private Integer likeCount;

    private Integer dislikeCount;

    /**
     * 图片的json格式list
     */
    private String imgUrls;

    private String topicId;

    private String title;

    private String content;

    private Integer status;

    private String failReason;

    private Boolean isDeleted;

    private LocalDateTime publishTime;

    private LocalDateTime modifyTime;


}
