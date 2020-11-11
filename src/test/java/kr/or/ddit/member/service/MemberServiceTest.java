package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.model.MemberVo;

public class MemberServiceTest extends ModelTestConfig {

	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@Test
	public void INSERTMEMBER_SUCCESS_Test() {
		/***Given***/
		MemberVo mv = new MemberVo("SUCCESS_ID", "SUCCESS_PASS", "SUCCESS", "SUCCESS",
								"", "", "", "", "");
		
		/***When***/
		int insertCnt = memberService.insertMember(mv);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	//@Test
	public void FAIL_Test() {
		/***Given***/
		MemberVo mv = new MemberVo("SUCCESS_ID", "SUCCESS_PASS", "SUCCESS", "SUCCESS",
								"", "", "", "", "");
		
		/***When***/
		int insertCnt = memberService.insertMember(mv);
		/***Then***/
		assertEquals(1, insertCnt);
		
		// 
	}

}
