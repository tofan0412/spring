package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAdvice.class);
	
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("beforemethod : {}", joinPoint.getSignature().getName());
		
	}
	
	@Around("dummy()")
	// Around Method 의 경우 첫번째 매개변수로 반드시 ProceedingJoinPoint 유형의 변수를 선언해야 한다.
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		// 메서드 실행 전 공통 관심사항
		long start = System.currentTimeMillis();
		// 메서드 실행
		Object ret = joinPoint.proceed(joinPoint.getArgs());	// 메서드에 대한 실행
		
		// 메서드 실행 후 공통 관심사항
		long end = System.currentTimeMillis();
		logger.debug("profiling : {} {} - {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				end - start + "ms");
		
		return ret;
	}
	
	// pointcut 설정을 위한 의미없는 가짜 메서드..
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
		
		
		
	}
}
