package com.kkwrite.gallery;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

	@Bean
	public DataSource createDataSource() {
		// TODO 配置需要需改为配置文件
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://www.artlyt.com.cn:3306/gallery?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull");
		ds.setUsername("root");
		ds.setPassword("7890uiop");
		return ds;
	}
	
}
