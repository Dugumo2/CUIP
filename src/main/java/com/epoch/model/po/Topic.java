package com.epoch.model.po;

import java.math.BigDecimal;
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
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
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


}
