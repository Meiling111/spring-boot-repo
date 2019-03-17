package com.example.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 使用AOP统一处理Web请求日志
 * @author gaomeiling
 *
 */
@Aspect //定义切面类
@Order(5) //标识切面的优先级，值越小优先级越高
@Component
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 基本类型，会出现多个线程冲突问题
	 * ThreadLocal代表了一个线程局部的变量，每条线程都只能看到自己的值，并不会意识到其它的线程中也存在该变量。
	 */
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("execution(public * com.example.web..*.*(..))") //定义一个切入点并指明扫描位置
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		startTime.set(System.currentTimeMillis());
		
		//接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		//记录下请求内容
		logger.info("url:"+request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning="ret",pointcut="webLog()")
	public void doAfterReturning(Object ret) throws Throwable{
		//处理完请求，返回内容
		logger.info("response:"+ret);
		logger.info("spend time:"+(System.currentTimeMillis()-startTime.get()));
	}
	
}
