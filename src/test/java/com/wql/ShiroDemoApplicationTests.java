package com.wql;

import com.wql.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = {"com.wql.mapper"})
public class ShiroDemoApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		System.out.println(userService.findByName("soso"));
	}

}
