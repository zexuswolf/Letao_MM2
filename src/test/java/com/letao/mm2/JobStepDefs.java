package com.letao.mm2;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = App.class)
@SpringBootTest
@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
public class JobStepDefs {
	
	//@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobLauncher jobLauncher;
	@Autowired
	private Job createMatrialBatchJob;
	private JobExecution jobExecution;
	
	private String file = "";

	@Given("^csv file \"([^\"]*)\"$")
	public void csv_file(String fname) throws Throwable {
	    file = fname;
	}
	
	@Bean
	public JobLauncherTestUtils createJobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}
	

	@When("^execute job : createMaterialBatchJob$")
	public void execute_job_createMaterialBatchJob() throws Throwable {
		JobParametersBuilder builder = new JobParametersBuilder();
			builder.addString("file",file);
		
		if(createMatrialBatchJob==null)
			System.out.println("Job null");
		else {
			System.out.println("Job present");
			System.out.println("Job : "+createMatrialBatchJob.getName());
		}
		
		jobLauncherTestUtils = createJobLauncherTestUtils();
			jobLauncherTestUtils.setJob(createMatrialBatchJob);
		JobParameters params = builder.toJobParameters();
		if(params==null)
			System.out.println("Parameter null");
		else {
			System.out.println("Parameter present");
			//System.out.println("Job : "+createMatrialBatchJob.getName());
		}
		jobLauncherTestUtils.getJobLauncher();
		//jobExecution = jobLauncherTestUtils.launchJob(params);
		jobExecution = jobLauncher.run(createMatrialBatchJob,params);
	}

	@Then("^the job should be \"([^\"]*)\"$")
	public void the_job_should_be(String status) throws Throwable {
	    assertEquals(status,jobExecution.getStatus().toString());
	}
	
}
