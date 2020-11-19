package kr.or.ddit.batch.basic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/db/spring/batch-context.xml"})
public class BasicJobTest {
	
	
	// batch 테스트 시 주의사항
	// spring container에 job이 오직 하나만 등록되어 있어야 한다.
	@Autowired 
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void basicJobTest() throws Exception {
		/***Given***/
		

		/***When***/
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();	// batch 에 대한 실행 정보를 반환한다. 
		/***Then***/
		// 정상적으로 실행되었다면, 상태가 completed 일 것이다. (getExitStatus는 batch 수행 후의 상태를 반환)
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		
		
	}

}
