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
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.db.model.SocialHobbyExample;

public interface SocialHobbyMapper {
    @SelectProvider(type=SocialHobbySqlProvider.class, method="countByExample")
    long countByExample(SocialHobbyExample example);

    @DeleteProvider(type=SocialHobbySqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialHobbyExample example);

    @Delete({
        "delete from social_hobby",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_hobby (channel, user_id, ",
        "content, valid, ",
        "create_time, update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{content,jdbcType=VARCHAR}, #{valid,jdbcType=TINYINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SocialHobby record);

    @InsertProvider(type=SocialHobbySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SocialHobby record);

    @SelectProvider(type=SocialHobbySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialHobby> selectByExample(SocialHobbyExample example);

    @Select({
        "select",
        "id, channel, user_id, content, valid, create_time, update_time",
        "from social_hobby",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialHobby selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialHobbySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialHobby record, @Param("example") SocialHobbyExample example);

    @UpdateProvider(type=SocialHobbySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialHobby record, @Param("example") SocialHobbyExample example);

    @UpdateProvider(type=SocialHobbySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialHobby record);

    @Update({
        "update social_hobby",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "content = #{content,jdbcType=VARCHAR},",
          "valid = #{valid,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialHobby record);
}