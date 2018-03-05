package com.letao.mm2;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.letao.mm2.model.Material;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
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
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
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

	@When("^call POST \"([^\"]*)\" with material data$")
	public void call_POST_with_material_data(String uri, DataTable table) throws Throwable {
		List<String> arr = table.topCells();
		Material mat = new Material();
			mat.setId(Long.parseLong(arr.get(0)));
			mat.setThaiName(arr.get(1));
			mat.setEnglishName(arr.get(2));
			mat.setMaterialTypeId(Long.parseLong(arr.get(3)));
	    requestBuilder = post(uri).contentType(MediaType.APPLICATION_JSON).content( json(mat) );
	}
	
	@When("^call DELETE \"([^\"]*)\"$")
	public void call_DELETE(String uri) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		requestBuilder = delete(uri);
	}
	
	@When("^call PUT \"([^\"]*)\" with material data$")
	public void call_PUT_with_material_data(String uri, DataTable table) throws Throwable {
		List<String> arr = table.topCells();
		Material mat = new Material();
			mat.setId(Long.parseLong(arr.get(0)));
			mat.setThaiName(arr.get(1));
			mat.setEnglishName(arr.get(2));
			mat.setMaterialTypeId(Long.parseLong(arr.get(3)));
	    requestBuilder = put(uri).contentType(MediaType.APPLICATION_JSON).content( json(mat) );
	}

	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }
	
	@SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
}
