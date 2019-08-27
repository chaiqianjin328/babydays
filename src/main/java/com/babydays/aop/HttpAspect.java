package com.babydays.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Aspect
@Slf4j
public class HttpAspect {

	
	@Pointcut("execution(public * com.babydays.controller..*.*(..))")
	public void pointcut() {
		
	}
	
	@Before("pointcut()")
	public void beforeMethod(JoinPoint joinPoint) {
		log.info("方法执行之前");
		log.info(((MethodSignature)joinPoint.getSignature()).getMethod().getName());
		String[] parameterNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			stringBuilder.append("(参数:参数值)--"+parameterNames[i]+":"+args[i]+";");
		}
		log.info(stringBuilder.toString());
	}
	
	@After("pointcut()")
	public void afterMethod(JoinPoint joinPoint) {
		log.info("方法执行之后");
		log.info(((MethodSignature)joinPoint.getSignature()).getMethod().getName());
		String[] parameterNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			stringBuilder.append("(参数:参数值)--"+parameterNames[i]+":"+args[i]+";");
		}
		log.info(stringBuilder.toString());
	}
	
	@AfterReturning(pointcut="pointcut()",returning="result")
	public void afterReturningMethod(JoinPoint joinPoint,Object result) {
		log.info("方法执行之后返回,返回值:"+result);
		log.info(((MethodSignature)joinPoint.getSignature()).getMethod().getName());
		String[] parameterNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			stringBuilder.append("(参数:参数值)--"+parameterNames[i]+":"+args[i]+";");
		}
		log.info(stringBuilder.toString());
	}
	
	@AfterThrowing(pointcut="pointcut()",throwing="e")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception e) {
		log.info("方法执行之后抛出异常");
		log.info(((MethodSignature)joinPoint.getSignature()).getMethod().getName());
		String[] parameterNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			stringBuilder.append("(参数:参数值)--"+parameterNames[i]+":"+args[i]+";");
		}
		log.info(stringBuilder.toString());
	}
	
	@Around("pointcut()")
	public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info("方法环绕之前");
		log.info(((MethodSignature)proceedingJoinPoint.getSignature()).getMethod().getName());
		String[] parameterNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
		Object[] args = proceedingJoinPoint.getArgs();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			stringBuilder.append("(参数:参数值)--"+parameterNames[i]+":"+args[i]+";");
		}
		log.info(stringBuilder.toString());
		Object object = proceedingJoinPoint.proceed();
		log.info("方法环绕之后,返回值:"+object);
		return object;
	}
	
	
	
}
