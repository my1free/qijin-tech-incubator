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
import tech.qijin.incubator.social.db.model.SocailCards;
import tech.qijin.incubator.social.db.model.SocailCardsExample;

public interface SocailCardsMapper {
    @SelectProvider(type=SocailCardsSqlProvider.class, method="countByExample")
    long countByExample(SocailCardsExample example);

    @DeleteProvider(type=SocailCardsSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocailCardsExample example);

    @Delete({
        "delete from socail_cards",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into socail_cards (channel, create_time, ",
        "update_time)",
        "values (#{channel,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SocailCards record);

    @InsertProvider(type=SocailCardsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SocailCards record);

    @SelectProvider(type=SocailCardsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SocailCards> selectByExample(SocailCardsExample example);

    @Select({
        "select",
        "id, channel, create_time, update_time",
        "from socail_cards",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="channel", property="channel", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SocailCards selectByPrimaryKey(Long id);

    @UpdateProvider(type=SocailCardsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocailCards record, @Param("example") SocailCardsExample example);

    @UpdateProvider(type=SocailCardsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocailCards record, @Param("example") SocailCardsExample example);

    @UpdateProvider(type=SocailCardsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SocailCards record);

    @Update({
        "update socail_cards",
        "set channel = #{channel,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SocailCards record);
}