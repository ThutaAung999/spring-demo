package com.turingjavaee5batch.demo.aop;

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

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

	@Pointcut("@annotation(Log)")
	public void logPointcut() {
	}

	/* @Before("logPointcut()") */
	@Before("execution(* com.turingjavaee5batch.demo.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("In Aspect  before: " + joinPoint.toString());
	}

	@After("execution(* com.turingjavaee5batch.demo.controller.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("In Aspect  after: " + joinPoint.toString());
	}

	@Before("within(com.turingjavaee5batch.demo.controller..*))")
	public void withinBefore(JoinPoint joinPoint) {
		log.info("In Aspect  within before: " + joinPoint.toString());
	}

	@AfterReturning(pointcut = "execution(* com.turingjavaee5batch.demo.controller.*.*(..))", returning = "retVal")
	public void doAccessCheck(Object retVal) {
		log.info("In Aspect AfterReturning :    return value :" + retVal);
	}

	@AfterThrowing(value = "execution(* com.turingjavaee5batch.demo.controller.*.*(..))", throwing = "exception")
	public void doRecoveryActions(Exception exception) {
		log.info("In Aspect AfterThrowing  :  " + exception.getMessage());
	}

	 @Around("execution(* com.turingjavaee5batch.demo.services.*.*(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		log.info("In Aspect Around  Before :  " + pjp.toString());

		for(Object param:pjp.getArgs()) {
			log.info("Parameter :"+param);
		}
		log.info("Proxy : "+pjp.getThis());
		log.info("Target  : "+pjp.getTarget());
		//log.info("Signature  : "+pjp.getSignature());
		log.info("Signature Name : "+pjp.getSignature().getName());
		log.info("Signature DeclaringTypeName : "+pjp.getSignature().getDeclaringTypeName());
		
		Object retVal = null;
		 retVal = pjp.proceed();

		log.info("In Aspect Around  After :  " + pjp.toString() + " ,  Return : " + retVal);

		return retVal;
	}

}