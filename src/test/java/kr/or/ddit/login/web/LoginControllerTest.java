package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

//Why? loginController가 웹기반으로 돌아가기 때문에 해당 환경을 만들어야 한다.
public class LoginControllerTest extends WebTestConfig{
	@Test
	public void getViewTest() throws Exception {
		// andExpect : assert와 동일하다.
		mockMvc.perform(get("/login/view"))
		.andExpect(status().isOk())
		.andExpect(view().name("login/view")); 
	}
	
	@Test
	// 로그인 요청 메서드( 사용자가 입력한 아이디와 비밀번호가 맞는 경우 )
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process")
		.param("userid", "brown")
		.param("pass", "brownPass"))
		.andExpect(status().is(200))
		.andExpect(view().name("main"))
		.andExpect(model().attributeExists("to_day"));
	}
	
	@Test
	// 로그인 요청 메서드( 사용자가 입력한 아이디와 비밀번호가 맞지 않는 경우 )
	public void processFailureTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/login/process")
								.param("userid", "brown")
								.param("pass", "brownPassFail"))
								.andReturn();
		// Model과 View 객체를 함께 관리하는 객체이다.
		ModelAndView mav = result.getModelAndView();
		assertEquals("login/view", mav.getViewName());
		assertEquals("fail", mav.getModel().get("msg"));
	}
}
