package com.inca.skyws;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SkyApplication {

	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager) {
		System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}

	@Bean
	public PlatformTransactionManager txManager(DataSource dataSource) throws SQLException {
		System.out.println(">>>>>>>>>>"+dataSource);
		
		System.out.println(">>>>>>>>>>"+dataSource.getConnection().getClientInfo());
		return new DataSourceTransactionManager(dataSource);
	}

	public static void main(String[] args) {
		System.err.println("项目启动...");
		SpringApplication.run(SkyApplication.class, args);
		System.err.println("项目启动ok");
	}
}
