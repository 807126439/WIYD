package com.spr.core.spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	
	
	
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.wb.web.system.service..*(..))")
	public void aspect(){}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint){
		System.out.println("before");
	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint){
		System.out.println("after");
	}
	
//	//配置环绕通知,使用在方法aspect()上注册的切入点
//	@Around("aspect()")
//	public void around(JoinPoint joinPoint){
//		System.out.println("around");
//		long start = System.currentTimeMillis();
//		try {
//			((ProceedingJoinPoint) joinPoint).proceed();
//			long end = System.currentTimeMillis();
//			
//		} catch (Throwable e) {
//			long end = System.currentTimeMillis();
//			
//		}
//	}
//	
//	//配置后置返回通知,使用在方法aspect()上注册的切入点
//	@AfterReturning("aspect()")
//	public void afterReturn(JoinPoint joinPoint){
//		System.out.println("afterReturn");
//	}
//	
//	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
//	@AfterThrowing(pointcut="aspect()", throwing="ex")
//	public void afterThrow(JoinPoint joinPoint, Exception ex){
//		System.out.println("afterThrow");
//	}
	

}
