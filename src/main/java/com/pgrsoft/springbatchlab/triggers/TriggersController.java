package com.pgrsoft.springbatchlab.triggers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggersController {

	@Autowired
//	@Qualifier("syncJobLauncher")
	@Qualifier("asyncJobLauncher")
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job1;
	
	@Autowired
	private Job job2;
	
	@Autowired
	private Job job3;
	
	@RequestMapping("/job1")
	public String job1() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String,JobParameter> jobParametersMap = new HashMap<>();
		
		jobParametersMap.put("parametro1", new JobParameter("p_" + System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(jobParametersMap);
		
		jobLauncher.run(job1, jobParameters);
		
		return "ok";
	}
	
	//*******************************************************************
	
	@RequestMapping("/job2")
	public String job2() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String,JobParameter> jobParametersMap = new HashMap<>();
		
		jobParametersMap.put("parametro1", new JobParameter("p_" + System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(jobParametersMap);
		
		JobExecution jobExecution = jobLauncher.run(job2, jobParameters);
		
		return "estado job2: " + jobExecution.getStatus().toString();
	}
	
	//*******************************************************************
	
	@RequestMapping("/job3")
	public String job3() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		Map<String,JobParameter> jobParametersMap = new HashMap<>();
		
		jobParametersMap.put("parametro1", new JobParameter("p_" + System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(jobParametersMap);
		
		jobLauncher.run(job3, jobParameters);
		
		return "ok";
	}
	
	
	
}
