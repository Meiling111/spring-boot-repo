package com.example.exception;

/**
 * 创建一个自定义异常，用来实验捕获该异常，并返回json
 * @author gaomeiling
 *
 */
public class MyException extends Exception{

	public MyException(String message) {
		super(message);
	}
}
