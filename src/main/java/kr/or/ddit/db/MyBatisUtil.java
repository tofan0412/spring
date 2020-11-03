package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	// static 블럭에서 사용하기 위해선 static으로 선언해야 한다. 
	private static SqlSessionFactory sqlSessionFactory;
	
	// static 블럭 통해 MyBatis 환경을 구성한다.
	// ==> sqlSessionFactory 객체를 생성
	static {
		String resource = "kr/or/ddit/config/db/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// sqlSessionFactory 객체를 통해 sqlSession 객체를 얻어내는 메서드를 생성
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
}

