package com.letao.mm2.job.create_material_batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class CreateMaterialBatchCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(CreateMaterialBatchCompletionNotificationListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		String msg = String.format("Starting job { id : %d , jobId : %d , instanceId : %d , jobName : %s , version : %d }",
				jobExecution.getId(),
				jobExecution.getJobId(),
				jobExecution.getJobInstance().getInstanceId(),
				jobExecution.getJobInstance().getJobName(),
				jobExecution.getJobInstance().getVersion());
		log.info(msg);
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		String msg = String.format("Finished { id : %d , jobId : %d , instanceId : %d , jobName : %s , version : %d , status : }",
				jobExecution.getId(),
				jobExecution.getJobId(),
				jobExecution.getJobInstance().getInstanceId(),
				jobExecution.getJobInstance().getJobName(),
				jobExecution.getJobInstance().getVersion(),
				jobExecution.getStatus().toString());
		log.info(msg);
	}

	

	
	
}
