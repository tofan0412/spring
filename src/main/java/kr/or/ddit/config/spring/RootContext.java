package kr.or.ddit.config.spring;

import org.springframework.aop.framework.AopContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


// java 설정 파일에서 xml 파일 불러오기
// @ImportResource({"classpath:kr/or/ddit/config/db/spring/aop-context.xml"})

// java 설정 파일에서 다른 java 설정 파일 불러오기
// @Import({AopContext.class, DataSourceContext.class, TransactionContext.class})

//이 class가 Bean config file임을 명시
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
				includeFilters = {@Filter(type=FilterType.ANNOTATION, 
				classes = {Service.class, Repository.class})})
public class RootContext {
	@Bean()
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:kr/or/ddit/message/error",
									"classpath:kr/or/ddit/message/msg");
		return messageSource;
	}

}
