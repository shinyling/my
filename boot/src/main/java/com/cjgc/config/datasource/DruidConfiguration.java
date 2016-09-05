package com.cjgc.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author shiny
 * @since 2016/9/2 17:39
 */
@Configuration
public class DruidConfiguration {

    private static final Logger log= LoggerFactory.getLogger(DruidConfiguration.class);

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Bean
    public ServletRegistrationBean druidServlet(){
        log.info("配置druid数据源监控");
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        servletRegistrationBean.addInitParameter("deny","192.168.0.1");
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }


    @Bean(initMethod = "init",destroyMethod = "close")
    public DataSource druidDataSource(){
        log.info("配置druid数据源！");
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setInitialSize(dataSourceProperties.getInitialSize());
        druidDataSource.setMaxActive(dataSourceProperties.getMaxActive());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMinIdle(dataSourceProperties.getMinIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
        try {
            druidDataSource.setFilters(dataSourceProperties.getFilters());
        } catch (SQLException e) {
            log.error("设置druidDatasource错误!");
        }
        return druidDataSource;
    }


    @Bean
    public FilterRegistrationBean druidStatFilter(){
        log.info("配置druid filter");
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
