package com.epoch.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopicVo {
    @TableId
    private String topicId;

    private BigDecimal score;

    /**
     * 标签ID的json格式list
     */
    private String labelIds;

    private String title;

    private String bio;

    private String imgUrl;

    private Integer postCounts;
}
