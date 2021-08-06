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
import tech.qijin.incubator.social.db.model.SocialGroupMember;
import tech.qijin.incubator.social.db.model.SocialGroupMemberExample;

public interface SocialGroupMemberMapper {
    @SelectProvider(type=SocialGroupMemberSqlProvider.class, method="countByExample")
    long countByExample(SocialGroupMemberExample example);

    @DeleteProvider(type=SocialGroupMemberSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialGroupMemberExample example);

    @Delete({
        "delete from social_group_member",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into social_group_member (channel, group_id, ",
        "user_id, is_admin, ",
        "status, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{groupId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{isAdmin,jdbcType=TINYINT}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SocialGroupMember record);

    @InsertProvider(type=SocialGroupMemberSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SocialGroupMember record);

    @SelectProvider(type=SocialGroupMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="is_admin", property="isAdmin", jdbcType=JdbcType.TINYINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocialGroupMember> selectByExample(SocialGroupMemberExample example);

    @Select({
        "select",
        "id, channel, group_id, user_id, is_admin, status, create_time, update_time",
        "from social_group_member",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="is_admin", property="isAdmin", jdbcType=JdbcType.TINYINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocialGroupMember selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocialGroupMemberSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialGroupMember record, @Param("example") SocialGroupMemberExample example);

    @UpdateProvider(type=SocialGroupMemberSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialGroupMember record, @Param("example") SocialGroupMemberExample example);

    @UpdateProvider(type=SocialGroupMemberSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocialGroupMember record);

    @Update({
        "update social_group_member",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "group_id = #{groupId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "is_admin = #{isAdmin,jdbcType=TINYINT},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocialGroupMember record);
}