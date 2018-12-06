package com.wql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.wql.mapper"})
public class ShiroDemoApplication {

	//启动类
	public static void main(String[] args) {
		SpringApplication.run(ShiroDemoApplication.class, args);
	}
}
