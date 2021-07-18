package tech.qijin.incubator.social.db.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import tech.qijin.incubator.social.db.model.SocialActivityParticipant;
import tech.qijin.incubator.social.db.model.SocialActivityParticipantExample;

public interface SocialActivityParticipantMapper {
    @SelectProvider(type=SocialActivityParticipantSqlProvider.class, method="countByExample")
    long countByExample(SocialActivityParticipantExample example);

    @DeleteProvider(type=SocialActivityParticipantSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialActivityParticipantExample example);

    @Delete({
        "delete from social_activity_participant",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_activity_participant (id, channel, ",
        "activity_id, user_id, ",
        "contact, status, ",
        "create_time, update_time)",
        "values (#{id,jdbcType=BIGINT}, #{channel,jdbcType=VARCHAR}, ",
        "#{activityId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{contact,jdbcType=VARCHAR}, #{stauts,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(SocialActivityParticipant record);

    @InsertProvider(type=SocialActivityParticipantSqlProvider.class, method="insertSelective")
    int insertSelective(SocialActivityParticipant record);

    @SelectProvider(type=SocialActivityParticipantSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="activity_id", property="activityId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="stauts", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialActivityParticipant> selectByExample(SocialActivityParticipantExample example);

    @Select({
        "select",
        "id, channel, activity_id, user_id, contact, status, create_time, update_time",
        "from social_activity_participant",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="activity_id", property="activityId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="stauts", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialActivityParticipant selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialActivityParticipantSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialActivityParticipant record, @Param("example") SocialActivityParticipantExample example);

    @UpdateProvider(type=SocialActivityParticipantSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialActivityParticipant record, @Param("example") SocialActivityParticipantExample example);

    @UpdateProvider(type=SocialActivityParticipantSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialActivityParticipant record);

    @Update({
        "update social_activity_participant",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "activity_id = #{activityId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "status = #{stauts,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialActivityParticipant record);
}