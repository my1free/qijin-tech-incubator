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
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityExample;

public interface SocialActivityMapper {
    @SelectProvider(type=SocialActivitySqlProvider.class, method="countByExample")
    long countByExample(SocialActivityExample example);

    @DeleteProvider(type=SocialActivitySqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialActivityExample example);

    @Delete({
        "delete from social_activity",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_activity (id, channel, ",
        "title, sponsor, contact, ",
        "status, create_time, ",
        "update_time, start_time, ",
        "end_time, tags, ",
        "location, lng, lat, ",
        "description)",
        "values (#{id,jdbcType=BIGINT}, #{channel,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{sponsor,jdbcType=BIGINT}, #{contact,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{startTime,jdbcType=TIMESTAMP}, ",
        "#{endTime,jdbcType=TIMESTAMP}, #{tags,jdbcType=VARCHAR}, ",
        "#{location,jdbcType=VARCHAR}, #{lng,jdbcType=VARCHAR}, #{lat,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=LONGVARCHAR})"
    })
    int insert(SocialActivity record);

    @InsertProvider(type=SocialActivitySqlProvider.class, method="insertSelective")
    int insertSelective(SocialActivity record);

    @SelectProvider(type=SocialActivitySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="sponsor", property="sponsor", jdbcType=JdbcType.BIGINT),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="lng", property="lng", jdbcType=JdbcType.VARCHAR),
        @Result(column="lat", property="lat", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SocialActivity> selectByExampleWithBLOBs(SocialActivityExample example);

    @SelectProvider(type=SocialActivitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="sponsor", property="sponsor", jdbcType=JdbcType.BIGINT),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="lng", property="lng", jdbcType=JdbcType.VARCHAR),
        @Result(column="lat", property="lat", jdbcType=JdbcType.VARCHAR)
    })
    List<SocialActivity> selectByExample(SocialActivityExample example);

    @Select({
        "select",
        "id, channel, title, sponsor, contact, status, create_time, update_time, start_time, ",
        "end_time, tags, location, lng, lat, description",
        "from social_activity",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="sponsor", property="sponsor", jdbcType=JdbcType.BIGINT),
        @Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="tags", property="tags", jdbcType=JdbcType.VARCHAR),
        @Result(column="location", property="location", jdbcType=JdbcType.VARCHAR),
        @Result(column="lng", property="lng", jdbcType=JdbcType.VARCHAR),
        @Result(column="lat", property="lat", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    SocialActivity selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialActivitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialActivity record, @Param("example") SocialActivityExample example);

    @UpdateProvider(type=SocialActivitySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SocialActivity record, @Param("example") SocialActivityExample example);

    @UpdateProvider(type=SocialActivitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialActivity record, @Param("example") SocialActivityExample example);

    @UpdateProvider(type=SocialActivitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialActivity record);

    @Update({
        "update social_activity",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "sponsor = #{sponsor,jdbcType=BIGINT},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
          "tags = #{tags,jdbcType=VARCHAR},",
          "location = #{location,jdbcType=VARCHAR},",
          "lng = #{lng,jdbcType=VARCHAR},",
          "lat = #{lat,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(SocialActivity record);

    @Update({
        "update social_activity",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "sponsor = #{sponsor,jdbcType=BIGINT},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "start_time = #{startTime,jdbcType=TIMESTAMP},",
          "end_time = #{endTime,jdbcType=TIMESTAMP},",
          "tags = #{tags,jdbcType=VARCHAR},",
          "location = #{location,jdbcType=VARCHAR},",
          "lng = #{lng,jdbcType=VARCHAR},",
          "lat = #{lat,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialActivity record);
}