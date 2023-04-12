package com.planner.godsaeng;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GodsaengApplicationTests {

	@Test
	void contextLoads() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
	}
	

}
