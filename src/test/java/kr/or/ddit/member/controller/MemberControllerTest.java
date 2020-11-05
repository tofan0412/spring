package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;
import kr.or.ddit.member.model.MemberVo;

public class MemberControllerTest extends WebTestConfig {

	@Test
	public void getMemberRegistTest() throws Exception{
		/***Given***/
		
		/***When***/

		/***Then***/
		mockMvc.perform(get("/member/memberRegist"))
				.andExpect(status().isOk())
				.andExpect(view().name("/member/memberRegist")); 
	}
	
	@Test
	public void mainTest() throws Exception{
		/***Given***/
	
		/***When***/
		
		/***Then***/
		mockMvc.perform(get("/member/main"))
		.andExpect(status().isOk())
		.andExpect(view().name("main"));
	}
	
	@Test
	public void RegistTest() throws Exception {
		/***Given***/
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
	    MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", is);
		/***When***/
		MvcResult result = mockMvc.perform(fileUpload("/member/Regist")
				  .file(file)
				  .param("userid", "테스트 입력")
				  .param("pass", "테스트 입력")
				  .param("usernm", "테스트 입력")
				  .param("alias", "테스트 입력")
				  .param("addr1", "대전 중구 대흥동")
				  .param("addr2", "주소지 신규입력")
				  .param("zipcode", "35364")).andReturn();
		/***Then***/
		ModelAndView mav = result.getModelAndView();
		MemberVo memberVo = (MemberVo) mav.getModel().get("memberVo");
		assertEquals("테스트 입력", memberVo.getUserid());
	}
	
	@Test
	public void getMemberListTest() throws Exception {
		/***Given***/

		/***When***/

		/***Then***/
		mockMvc.perform(get("/member/getMemberList"))
				.andExpect(status().isOk())
				.andExpect(view().name("/member/memberList"));
	}
	
	@Test
	public void getMemberList_pTest() throws Exception{
		/***Given***/

		/***When***/

		/***Then***/
		mockMvc.perform(get("/member/getMemberList_P").param("page", "2")
				.param("pageSize", "5"))
				.andExpect(status().isOk())
				.andExpect(view().name("/member/memberList"));
	}
	
	@Test
	public void showMemberInfoTest() throws Exception{
		/***Given***/

		/***When***/
		MvcResult result = mockMvc.perform(get("/member/showMemberInfo")
				  				  .param("userid", "brown")).andReturn();	
		/***Then***/
		ModelAndView mav = result.getModelAndView();
		assertEquals("/member/member", mav.getViewName());
		MemberVo memberVo = (MemberVo) mav.getModel().get("memberVo");
		assertEquals("brown", memberVo.getUserid());
	}
	
	@Test
	public void showMemberInfo_uTest() throws Exception{
		/***Given***/
		

		/***When***/
		MvcResult result = mockMvc.perform(get("/member/showMemberInfo_u")
				  .param("userid", "brown")).andReturn();
		
		/***Then***/
		ModelAndView mav = result.getModelAndView();
		MemberVo memberVo = (MemberVo) mav.getModel().get("memberVo");
		assertEquals("brown", memberVo.getUserid());
	}
	
	// ? 파일 어떻게 하지...
	@Test
	public void memberUpdateTest() throws Exception {
		/***Given***/
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
	    MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", is);
		/***When***/
		MvcResult result = mockMvc.perform(fileUpload("/member/memberUpdate")
				  .file(file)
				  .param("userid", "brown")
				  .param("pass", "brownPass")
				  .param("usernm", "brown")
				  .param("alias", "brown")
				  .param("addr1", "대전 중구 대흥동")
				  .param("addr2", "주소지 수정함!")
				  .param("zipcode", "35364")).andReturn();
		/***Then***/
		ModelAndView mav = result.getModelAndView();
		MemberVo memberVo = (MemberVo) mav.getModel().get("memberVo");
		assertEquals("주소지 수정함!", memberVo.getAddr2());
	}
	
	@Test
	public void profileImgTest() throws Exception{
		
	}
	
	@Test
	public void profileDownloadTest() throws Exception{
		
	}
	

}
