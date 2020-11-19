package kr.or.ddit.multiparam.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

public class MultiParamControllerTest extends WebTestConfig{

	@Test
	public void multiParamViewTest() throws Exception{
		MvcResult result = mockMvc.perform(get("/multi/view")).andReturn();
		
		ModelAndView mav = result.getModelAndView();
		assertEquals("application/loginView", mav.getViewName());
	}
	
	
	
}


