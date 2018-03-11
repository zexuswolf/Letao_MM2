package com.letao.mm2.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	//@Autowired
	public ResponseEntity<?> listAllJobs(){
		//jobRepository.get
		return new ResponseEntity("",HttpStatus.OK);
	}
	
	
	@PostMapping("/createMaterialBatchJob")
	public ResponseEntity<?> launchCreateMaterialBatchJob(Job createMatrialBatchJob){
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
			jobParametersBuilder.addString("file","");
		JobParameters params = jobParametersBuilder.toJobParameters();
		try {
			jobLauncher.run(createMatrialBatchJob,jobParametersBuilder.toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity("",HttpStatus.OK);
	}
	
}
