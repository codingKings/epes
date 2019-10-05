package com.epes.demo;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 上午10:11:55
* @ClassName 类名称：
* @Description 类描述：
*/
/// 开启注解事务处理
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.epes.demo.dao")
public class EmployeePerformanceEvaluationSystemApplication extends WebMvcConfigurerAdapter {
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(EmployeePerformanceEvaluationSystemApplication.class, args);
    }
}
