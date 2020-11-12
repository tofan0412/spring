package kr.or.ddit.config.spring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

// 이 class가 Bean config file임을 명시 @Configuration
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
				includeFilters = {@Filter(type=FilterType.ANNOTATION, 
				classes = {Aspect.class})})
//@Aspect를 부여한 클래스에 대한 설정 처리
@EnableAspectJAutoProxy 
public class AopConext {
	
}
