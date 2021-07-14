package tech.qijin.incubator.social.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.incubator.social.db.model.SocialActivity;
import tech.qijin.incubator.social.db.model.SocialActivityExample.Criteria;
import tech.qijin.incubator.social.db.model.SocialActivityExample.Criterion;
import tech.qijin.incubator.social.db.model.SocialActivityExample;

public class SocialActivitySqlProvider {

    public String countByExample(SocialActivityExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("social_activity");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(SocialActivityExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("social_activity");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(SocialActivity record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("social_activity");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getSponsor() != null) {
            sql.VALUES("sponsor", "#{sponsor,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartTime() != null) {
            sql.VALUES("start_time", "#{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.VALUES("end_time", "#{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTags() != null) {
            sql.VALUES("tags", "#{tags,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.VALUES("location", "#{location,jdbcType=VARCHAR}");
        }
        
        if (record.getLng() != null) {
            sql.VALUES("lng", "#{lng,jdbcType=VARCHAR}");
        }
        
        if (record.getLat() != null) {
            sql.VALUES("lat", "#{lat,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(SocialActivityExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("title");
        sql.SELECT("sponsor");
        sql.SELECT("status");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("start_time");
        sql.SELECT("end_time");
        sql.SELECT("tags");
        sql.SELECT("location");
        sql.SELECT("lng");
        sql.SELECT("lat");
        sql.SELECT("description");
        sql.FROM("social_activity");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(SocialActivityExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("title");
        sql.SELECT("sponsor");
        sql.SELECT("status");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("start_time");
        sql.SELECT("end_time");
        sql.SELECT("tags");
        sql.SELECT("location");
        sql.SELECT("lng");
        sql.SELECT("lat");
        sql.FROM("social_activity");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        SocialActivity record = (SocialActivity) parameter.get("record");
        SocialActivityExample example = (SocialActivityExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("social_activity");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        }
        
        if (record.getSponsor() != null) {
            sql.SET("sponsor = #{record.sponsor,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTags() != null) {
            sql.SET("tags = #{record.tags,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.SET("location = #{record.location,jdbcType=VARCHAR}");
        }
        
        if (record.getLng() != null) {
            sql.SET("lng = #{record.lng,jdbcType=VARCHAR}");
        }
        
        if (record.getLat() != null) {
            sql.SET("lat = #{record.lat,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("social_activity");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("sponsor = #{record.sponsor,jdbcType=BIGINT}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        sql.SET("tags = #{record.tags,jdbcType=VARCHAR}");
        sql.SET("location = #{record.location,jdbcType=VARCHAR}");
        sql.SET("lng = #{record.lng,jdbcType=VARCHAR}");
        sql.SET("lat = #{record.lat,jdbcType=VARCHAR}");
        sql.SET("description = #{record.description,jdbcType=LONGVARCHAR}");
        
        SocialActivityExample example = (SocialActivityExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("social_activity");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("title = #{record.title,jdbcType=VARCHAR}");
        sql.SET("sponsor = #{record.sponsor,jdbcType=BIGINT}");
        sql.SET("status = #{record.status,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("start_time = #{record.startTime,jdbcType=TIMESTAMP}");
        sql.SET("end_time = #{record.endTime,jdbcType=TIMESTAMP}");
        sql.SET("tags = #{record.tags,jdbcType=VARCHAR}");
        sql.SET("location = #{record.location,jdbcType=VARCHAR}");
        sql.SET("lng = #{record.lng,jdbcType=VARCHAR}");
        sql.SET("lat = #{record.lat,jdbcType=VARCHAR}");
        
        SocialActivityExample example = (SocialActivityExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SocialActivity record) {
        SQL sql = new SQL();
        sql.UPDATE("social_activity");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getSponsor() != null) {
            sql.SET("sponsor = #{sponsor,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStartTime() != null) {
            sql.SET("start_time = #{startTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getEndTime() != null) {
            sql.SET("end_time = #{endTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getTags() != null) {
            sql.SET("tags = #{tags,jdbcType=VARCHAR}");
        }
        
        if (record.getLocation() != null) {
            sql.SET("location = #{location,jdbcType=VARCHAR}");
        }
        
        if (record.getLng() != null) {
            sql.SET("lng = #{lng,jdbcType=VARCHAR}");
        }
        
        if (record.getLat() != null) {
            sql.SET("lat = #{lat,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SocialActivityExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}