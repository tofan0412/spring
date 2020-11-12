package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 다른 자바 파일을 참고할 수 있게끔 한다.  
//@Import({DataSourceContext.class})

@EnableTransactionManagement	// java설정 파일을 기반으로 Transaction을 관리하겠다 !
@Configuration
public class TransactionContext {
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Bean
	PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager trasactionManager = 
				new DataSourceTransactionManager(dataSource);
				// dataSource 주입을 해야 한다. 
		return trasactionManager;
	}
	/*
	<tx:advice id="TxAdvisor">
	<aop:config> 
	위 두 부분에 해당하는 java 설정은 없음
	@Transactional 어노테이션을 트랜잭션으로 삼을 메서드나, 클래스 단위로 붙여준다.
	스프링 빈을 <bean> 엘리먼트를 통해 일일이 등록 ==> @Controller, @Service ,@Repository를 붙이는 것과 유사
	*/
	
}
