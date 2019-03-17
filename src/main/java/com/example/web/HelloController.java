package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.MyException;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "Hello World";
	}
	
	@RequestMapping(value="/hello_you",method=RequestMethod.GET)
	@ResponseBody
	public String hello(@RequestParam String name) {
		return "hello "+name;
	}
	
	/**
	 * 返回json格式的异常
	 * @return
	 * @throws MyException
	 */
	@RequestMapping("/json")
	public String json() throws MyException{
		throw new MyException("发生错误2");
	}
	
	/**
	 * 测试统一异常处理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hello_exception")
	public String hello_exception() throws Exception{
		throw new Exception("发生错误。");
	}
	
	/**
	 * 使用Thymeleaf渲染web视图
	 * @param map
	 * @return
	 */
	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("host","http://www.baidu.com");
		return "index";
	}

}
