package com.inca.skyws.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(public * com.inca.skyws.controller.*.*(..))")
	public void controllerLog() {
	}
	
	@Pointcut("execution(public * com.inca.skyws.service.*.*(..))")
	public void serviceLog() {
	}
	
	@Pointcut("execution(public * com.inca.skyws.socket.*.*(..))")
	public void socketLog() {
	}
	
	@Pointcut("execution(public * com.inca.skyws.security.*.*(..))")
	public void securityLog() {
	}

	@Before("controllerLog()")
	public void d0Before(JoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes==null) {
			return;
		}
		HttpServletRequest request = attributes.getRequest();
		Signature signature = joinPoint.getSignature();
		log.info("请求目标 : " + request.getMethod() + "	" + request.getRequestURL());
		log.info("来源IP : " + request.getRemoteAddr());
		log.info("处理类 : " + signature.getDeclaringTypeName() + "." + signature.getName());
		log.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
	}

	// 后置异常通知
	@AfterThrowing(pointcut="controllerLog()",throwing="exp")
	public void doAfterThrowing(Throwable exp) {
		log.info("方法异常:" + exp.getMessage(), exp);
	}

	// 环绕通知,环绕增强，相当于MethodInterceptor
	@Around("controllerLog()")
	public Object doAround(ProceedingJoinPoint pjp) {
		try {
			log.info("========================"+Thread.currentThread().getId()+"	start==============================");
			Object o = pjp.proceed();
			log.info("处理结果 : " + o);
			log.info("========================"+Thread.currentThread().getId()+"	end==============================");
			return o;
		} catch (Throwable e) {
			log.error("处理异常:" + e.getMessage(), e);
			log.info("========================" + Thread.currentThread().getId() + "	end==============================");
			return null;
		}
	}
	
	@Around("serviceLog()")
	public Object serviceAround(ProceedingJoinPoint pjp) {
		try {
			log.info("service处理...");
			Object o = pjp.proceed();
			log.info("service处理结果 : " + o);
			log.info("service处理end");
			return o;
		} catch (Throwable e) {
			log.error("service处理异常:" + e.getMessage(), e);
			return null;
		}
	}
	
	@Around("socketLog()")
	public Object socketAround(ProceedingJoinPoint pjp) {
		try {
			log.info("socket处理...");
			Object o = pjp.proceed();
			log.info("socket处理结果 : " + o);
			log.info("socket处理end");
			return o;
		} catch (Throwable e) {
			log.error("socket处理异常:" + e.getMessage(), e);
			return null;
		}
	}
	
	@Around("securityLog()")
	public Object securityAround(ProceedingJoinPoint pjp) {
		try {
			log.info("security处理...");
			Object o = pjp.proceed();
			log.info("security处理结果 : " + o);
			log.info("security处理end");
			return o;
		} catch (Throwable e) {
			log.error("security处理异常:" + e.getMessage(), e);
			return null;
		}
	}
}
