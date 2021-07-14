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
import tech.qijin.incubator.social.db.model.SocialActivityImage;
import tech.qijin.incubator.social.db.model.SocialActivityImageExample;

public interface SocialActivityImageMapper {
    @SelectProvider(type=SocialActivityImageSqlProvider.class, method="countByExample")
    long countByExample(SocialActivityImageExample example);

    @DeleteProvider(type=SocialActivityImageSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialActivityImageExample example);

    @Delete({
        "delete from social_activity_image",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_activity_image (id, channel, ",
        "activity_id, url, ",
        "status, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{channel,jdbcType=VARCHAR}, ",
        "#{activityId,jdbcType=BIGINT}, #{url,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(SocialActivityImage record);

    @InsertProvider(type=SocialActivityImageSqlProvider.class, method="insertSelective")
    int insertSelective(SocialActivityImage record);

    @SelectProvider(type=SocialActivityImageSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="activity_id", property="activityId", jdbcType=JdbcType.BIGINT),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialActivityImage> selectByExample(SocialActivityImageExample example);

    @Select({
        "select",
        "id, channel, activity_id, url, status, create_time, update_time",
        "from social_activity_image",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="activity_id", property="activityId", jdbcType=JdbcType.BIGINT),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialActivityImage selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialActivityImageSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialActivityImage record, @Param("example") SocialActivityImageExample example);

    @UpdateProvider(type=SocialActivityImageSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialActivityImage record, @Param("example") SocialActivityImageExample example);

    @UpdateProvider(type=SocialActivityImageSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialActivityImage record);

    @Update({
        "update social_activity_image",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "activity_id = #{activityId,jdbcType=BIGINT},",
          "url = #{url,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialActivityImage record);
}