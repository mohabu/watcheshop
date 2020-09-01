package com.cleevio.eshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cleevio.eshop.controller.WatchController;
import com.cleevio.eshop.model.Watch;
import com.cleevio.eshop.service.WatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value=WatchController.class)
class WatchControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WatchService watchService;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	  

	@Test
	public void getAllWatchesTest() throws Exception {

		Watch watchSample1 = new Watch();
		watchSample1.setDescription("A watch with a water fountain picture");
		watchSample1.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		watchSample1.setId(5);
		watchSample1.setPrice(5000);
		watchSample1.setTitle("Nokia");
		
		Watch watchSample2 = new Watch();
		watchSample2.setDescription("A watch with a water fountain picture");
		watchSample2.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		watchSample2.setId(5);
		watchSample2.setPrice(6000);
		watchSample2.setTitle("Casio");
		
		List<Watch> watchList = new ArrayList<>();
		watchList.add(watchSample1);
		watchList.add(watchSample2);
		
		
		Mockito.when(watchService.getAllWatches()).thenReturn(watchList);
		
		String URI = "/eshop//watches";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		String expectedJson = mapToJson(watchList);
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void getWatchByIdTest() throws Exception {
		Watch mockWatch = new Watch();
		mockWatch.setDescription("A watch with a water fountain picture");
		mockWatch.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockWatch.setId(2);
		mockWatch.setPrice(5000);
		mockWatch.setTitle("Nokia");
		
		Mockito.when(watchService.getWatchByID(2)).thenReturn(mockWatch);
		
		String URI = "/eshop//watches/2";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockWatch);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	

	
	@Test
	public void createWatchTest() throws Exception {
		Watch mockWatch = new Watch();
		mockWatch.setDescription("A watch with a water fountain picture");
		mockWatch.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockWatch.setId(1);
		mockWatch.setPrice(2000);
		mockWatch.setTitle("Prim");
		
		
		String inputInJson = this.mapToJson(mockWatch);
		
		String URI = "/eshop/watches";
		
		Mockito.when(watchService.createWatch(Mockito.any(Watch.class))).thenReturn(mockWatch);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	
	@Test
	public void updateWatchTest() throws Exception {
	
		
		
		Watch mockWatch = new Watch();
		mockWatch.setDescription("A watch with a water fountain picture");
		mockWatch.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockWatch.setId(1);
		mockWatch.setPrice(5000);
		mockWatch.setTitle("Nokia");
		Mockito.when(watchService.updateWatch(mockWatch)).thenReturn(HttpStatus.OK);
	}
	
	
	 // in this method: Map an Object into a JSON String, Uses a Jackson ObjectMapper.
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	

}
