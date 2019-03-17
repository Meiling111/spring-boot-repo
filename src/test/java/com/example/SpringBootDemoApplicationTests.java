package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试类，模拟http请求
 * @author gaomeiling
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration //由于是web项目，Junit需要模拟ServletContext，因此需要加上WebAppConfiguration注释
public class SpringBootDemoApplicationTests {

	@Before
	public void init() {
		System.out.println("开始测试……");
	}

	@After
	public void after() {
		System.out.println("测试结束……");
	}
}
