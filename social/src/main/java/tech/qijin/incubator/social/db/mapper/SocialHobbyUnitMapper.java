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
import tech.qijin.incubator.social.db.model.SocialHobbyUnit;
import tech.qijin.incubator.social.db.model.SocialHobbyUnitExample;

public interface SocialHobbyUnitMapper {
    @SelectProvider(type=SocialHobbyUnitSqlProvider.class, method="countByExample")
    long countByExample(SocialHobbyUnitExample example);

    @DeleteProvider(type=SocialHobbyUnitSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialHobbyUnitExample example);

    @Delete({
        "delete from social_hobby_unit",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_hobby_unit (channel, content, ",
        "count, valid, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{count,jdbcType=INTEGER}, #{valid,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SocialHobbyUnit record);

    @InsertProvider(type=SocialHobbyUnitSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SocialHobbyUnit record);

    @SelectProvider(type=SocialHobbyUnitSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialHobbyUnit> selectByExample(SocialHobbyUnitExample example);

    @Select({
        "select",
        "id, channel, content, count, valid, create_time, update_time",
        "from social_hobby_unit",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="valid", property="valid", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialHobbyUnit selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialHobbyUnitSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialHobbyUnit record, @Param("example") SocialHobbyUnitExample example);

    @UpdateProvider(type=SocialHobbyUnitSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialHobbyUnit record, @Param("example") SocialHobbyUnitExample example);

    @UpdateProvider(type=SocialHobbyUnitSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialHobbyUnit record);

    @Update({
        "update social_hobby_unit",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER},",
          "valid = #{valid,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialHobbyUnit record);
}