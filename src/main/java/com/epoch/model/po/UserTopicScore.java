package com.epoch.model.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户对话题评分表
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
public class UserTopicScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId

    private String userTopicScoreId;

    private String userId;

    private String topicId;

    /**
     * 1-5表示5个星级
     */
    private Integer score;


}
