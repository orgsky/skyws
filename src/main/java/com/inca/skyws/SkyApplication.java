package com.inca.skyws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkyApplication {

	public static void main(String[] args) {
		System.err.println("项目启动...");
		SpringApplication.run(SkyApplication.class, args);
		System.err.println("项目启动ok");
	}
}
