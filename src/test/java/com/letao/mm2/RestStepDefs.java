package com.letao.mm2;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = App.class)
@SpringBootTest
//@PropertySource("file:/D:/MyRepo/Local_Letao_MM_Repo/Letao_MM/src/main/resources/application.properties")
@WebAppConfiguration
public class RestStepDefs {
	
	@Autowired
    private WebApplicationContext webApplicationContext;

	//@Autowired
    private MockMvc mockMvc;
    private RequestBuilder requestBuilder;
    
    @Before
    public void setUpMockServer(){
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@When("^call GET \"([^\"]*)\"$")
	public void call_GET(String uri) throws Throwable {
		requestBuilder = get(uri);
	}

	@Then("^server should return status (\\d+)$")
	public void server_should_return_status(int status) throws Throwable {
	    mockMvc.perform(requestBuilder)
	    	.andExpect(status().is(status))
	    	.andDo(MockMvcResultHandlers.print());
	}

	
}
