package com.epoch.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PostVo {
    private String userId;

    private String nickname;

    private String avatarUrl;
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

    private Boolean isDeleted;

    private LocalDateTime publishTime;

    private LocalDateTime modifyTime;

}
