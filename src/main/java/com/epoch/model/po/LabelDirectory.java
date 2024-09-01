package com.epoch.model.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签目录
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LabelDirectory implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String labelId;

    private String labelName;


}
