package tech.qijin.incubator.social.db.dao;

import tech.qijin.incubator.social.db.mapper.SocialGroupMapper;
import tech.qijin.incubator.social.db.mapper.SocialGroupSqlProvider;
import tech.qijin.incubator.social.db.model.SocialGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.InsertProvider;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: SYSTEM
 **/

public interface SocialGroupDao extends SocialGroupMapper {

	@InsertProvider(type = SqlProvider.class, method = "batchInsert")
	int batchInsert(@Param("records") List<SocialGroup> records);

	class SqlProvider {
		private static final String VALUES = "VALUES";
		SocialGroupSqlProvider provider = new SocialGroupSqlProvider();

		public String batchInsert(Map<String, Object> param) {
			List<SocialGroup> records = (List<SocialGroup>) param.get("records");
			return genSql(records);
		}

		private String genSql(List<SocialGroup> records) {
			List<String> sqls = records.stream()
					.map(record -> provider.insertSelective(record))
					.collect(Collectors.toList());
			String[] arr = sqls.get(0).split(VALUES);
			String head = arr[0];
			String value = arr[1];
			List<String> values = Lists.newArrayList();
			for (int i = 0; i <= sqls.size() - 1; i++) {
				StringBuilder sb = new StringBuilder().append("#{records[").append(i).append("].");
				values.add(value.replace("#{", sb.toString()));
			}
			return new StringBuilder().append(head).append(" ").append(VALUES).append(" ")
					.append(StringUtils.join(values, ",")).toString();
		}
	}
}
