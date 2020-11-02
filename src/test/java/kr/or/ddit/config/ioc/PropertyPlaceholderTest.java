package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.DbProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
		{"classpath:kr/or/ddit/config/spring/ioc/property-placaholder.xml"})
public class PropertyPlaceholderTest {

	@Resource(name="dbProperty")
	private DbProperty dbProperty;
	
	@Test
	public void test() {
		/***Given***/
		
		/***When***/
		
		/***Then***/
		assertEquals("whc", dbProperty.getUser());
		assertEquals("java", dbProperty.getPass());
	}

}
