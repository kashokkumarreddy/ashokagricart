package com.ashokagricart.authservice;

import com.ashokagricart.authservice.config.TestSecurityMocks;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestSecurityMocks.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
