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
import tech.qijin.incubator.social.db.model.SocialGroup;
import tech.qijin.incubator.social.db.model.SocialGroupExample;

public interface SocialGroupMapper {
    @SelectProvider(type=SocialGroupSqlProvider.class, method="countByExample")
    long countByExample(SocialGroupExample example);

    @DeleteProvider(type=SocialGroupSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialGroupExample example);

    @Delete({
        "delete from social_group",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_group (channel, name, ",
        "creator, logo_small, ",
        "logo_big, description, ",
        "create_time, update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{creator,jdbcType=BIGINT}, #{logoSmall,jdbcType=VARCHAR}, ",
        "#{logoBig,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SocialGroup record);

    @InsertProvider(type=SocialGroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SocialGroup record);

    @SelectProvider(type=SocialGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="logo_small", property="logoSmall", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo_big", property="logoBig", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialGroup> selectByExample(SocialGroupExample example);

    @Select({
        "select",
        "id, channel, name, creator, logo_small, logo_big, description, create_time, ",
        "update_time",
        "from social_group",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.BIGINT),
        @Result(column="logo_small", property="logoSmall", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo_big", property="logoBig", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialGroup selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialGroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialGroup record, @Param("example") SocialGroupExample example);

    @UpdateProvider(type=SocialGroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialGroup record, @Param("example") SocialGroupExample example);

    @UpdateProvider(type=SocialGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialGroup record);

    @Update({
        "update social_group",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "creator = #{creator,jdbcType=BIGINT},",
          "logo_small = #{logoSmall,jdbcType=VARCHAR},",
          "logo_big = #{logoBig,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialGroup record);
}