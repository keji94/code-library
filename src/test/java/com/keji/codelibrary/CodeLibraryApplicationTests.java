package com.keji.codelibrary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//字符串到整数的转换，例如 “123” => 123 ，即实现 Integer.parseInt 功能。
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeLibraryApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		String str = "123";
		char[] chars = str.toCharArray();
		for (char aChar : chars) {
			
		}
	}

}
