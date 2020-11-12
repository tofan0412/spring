package kr.or.ddit.config.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

// <context:property-placeholder location="classpath:kr/or/ddit/config/db/db.properties"/>
@PropertySource("classpath:kr/or/ddit/config/db/db.properties")	// key값 만으로 설정할 수 있다.
@Configuration
@ComponentScan
public class DataSourceContext {
	@Autowired
	private Environment env;
	/*
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="url" value="${jdbc.url}"></property>
		<property name="driverClassName" value="${jdbc.driver}"/>
		<property name="username" value="${jdbc.user}"/>
		<property name="password" value="${jdbc.pass}"/>
	</bean>
	*/
	
	// <bean> ==> @Bean Annotaion과 동일하다.
	// property : setter method, 즉 field를 의미한다.
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		// 우리가 등록한 key 값을 가져올 수 있다. 
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		
		return dataSource;
	}
	
	@Bean
	public /*SqlSessionFactoryBean*/SqlSessionFactory SqlSessionFactoryBean() throws Exception{
		SqlSessionFactoryBean factoryBean = 
				new SqlSessionFactoryBean();
		// 2개의 속성을 추가해주면 된다 
		factoryBean.setConfigLocation(
				new ClassPathResource("kr/or/ddit/config/db/mybatis-config.xml"));
		factoryBean.setDataSource(dataSource());	// 위에서 작성한 Spring Bean 을 참조한다. 
		
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate SqlSessionTemplate() throws Exception{
		SqlSessionTemplate sqlSessionTemplate =
				new SqlSessionTemplate(SqlSessionFactoryBean());
		
		return sqlSessionTemplate;
	}
	
	
	
}
