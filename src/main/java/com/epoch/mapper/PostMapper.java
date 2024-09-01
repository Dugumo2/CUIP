package com.epoch.mapper;

import com.epoch.model.po.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.epoch.model.po.UserTopicScore;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 帖子 Mapper 接口
 * </p>
 *
 * @author liuhaha
 * @since 2024-07-18
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    @Insert("insert into post(user_id, post_id, like_count, dislike_count, img_urls, topic_id, title, content, status, fail_reason, is_deleted, publish_time, modify_time)  " +
            "values(#{userId}, #{postId}, #{likeCount}, #{dislikeCount}, #{imgUrls}, #{topicId}, #{title}, #{content}, #{status}, #{failReason}, #{isDeleted}, #{publishTime}, #{modifyTime})")
    void submitPost(Post post);

    @Select("select score from user_topic_score where user_id = #{userId} and topic_id = #{topicId}")
    Float getPostScore(@Param("userId") String userId, @Param("topicId")String topicId);

    @Select("select avg(score) from user_topic_score where topic_id = #{topicId}")
    Float getTopicScoreAvg(@Param("topicId")String topicId);

    @Update("update topic set score = #{scoreAvg} where topic_id = #{topicId}")
    void updateTopicScore(@Param("topicId")String topicId, @Param("scoreAvg") Double scoreAvg);


    @Insert("insert into user_topic_score(user_topic_score_id, user_id, topic_id, score) values(#{userTopicScoreId}, #{userId}, #{topicId}, #{score})")
    void submitPostScore(UserTopicScore userTopicScore);

    @Select("select count(*) from user_topic_score where user_id = #{userId} and topic_id = #{topicId}")
    int countUserTopicScore(@Param("userId") String userId, @Param("topicId") String topicId);

    default boolean isUserTopicScoreExists(String userId, String topicId) {
            return countUserTopicScore(userId, topicId) > 0;
    }

    @Update("update user_topic_score set score = #{score} where user_id = #{userId} and topic_id = #{topicId}")
    void updatePostScore(@Param("userId")String userId, @Param("topicId")String topicId, @Param("score")Integer score);
}
