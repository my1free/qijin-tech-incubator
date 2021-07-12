package tech.qijin.incubator.social.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tech.qijin.util4j.mybatis.handler.EnumValueTypeHandler;
import tech.qijin.util4j.mybatis.interceptor.ChannelInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author michealyang
 * @date 2019-11-12
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@MapperScan(basePackages = "tech.qijin.incubator.social.db.dao",
        sqlSessionFactoryRef = "socialSqlSessionFactory",
        sqlSessionTemplateRef = "socialSqlSessionTemplate")
public class SocialDatasourceConfig {

    @Primary
    @Bean("dataSourceSocial")
    @ConfigurationProperties("spring.datasource.druid.social")
    public DataSource dataSourceSocial() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "socialSqlSessionFactory")
    public SqlSessionFactory imSqlSessionFactory(@Qualifier("dataSourceSocial") DataSource dataSourceSocial)
            throws Exception {
        return getSqlSessionFactory(dataSourceSocial);
    }

    @Primary
    @Bean("socialSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("socialSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "socialTransactionManager")
    public DataSourceTransactionManager imTransactionManager(@Qualifier("dataSourceSocial") DataSource dataSourceSocial) {
        return new DataSourceTransactionManager(dataSourceSocial);
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setDefaultEnumTypeHandler(EnumValueTypeHandler.class);
        ChannelInterceptor channelInterceptor = new ChannelInterceptor();
        Properties channelProperties = new Properties();
        channelProperties.put("tenantColumnName", "channel");
        channelInterceptor.setProperties(channelProperties);
        configuration.addInterceptor(channelInterceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
