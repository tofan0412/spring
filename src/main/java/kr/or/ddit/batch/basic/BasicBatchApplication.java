package kr.or.ddit.batch.basic;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BasicBatchApplication {
	public static void main(String[] args) {
		// batch-context.xml을 이용하여 스프링 컨테이너를 생성한다.
		ApplicationContext context =
		new ClassPathXmlApplicationContext(
				"classpath:kr/or/ddit/config/db/spring/batch-context.xml");
		
		//jobLauncher, job 스프링 빈 DL
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job basicJob = context.getBean("basicJob", Job.class);
		
		try {
			jobLauncher.run(basicJob, new JobParametersBuilder()
					.addParameter("ym", new JobParameter("202011"))
					.addLong("st_dt", System.currentTimeMillis()).toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
