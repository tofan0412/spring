package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

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
		MemberVo mv = new MemberVo("temp", "SUCCESS_PASS", "SUCCESS", "SUCCESS",
								"", "", "", "", "");
		
		/***When***/
		int insertCnt = memberService.insertMember(mv);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	//@Test
//	public void FAIL_Test() {
//		/***Given***/
//		MemberVo mv = new MemberVo("SUCCESS_ID", "SUCCESS_PASS", "SUCCESS", "SUCCESS",
//								"", "", "", "", "");
//		
//		/***When***/
//		int insertCnt = memberService.insertMember(mv);
//		/***Then***/
//		assertEquals(1, insertCnt);
//		
//		// 
//	}
	
	@Test
	public void getMemberAllTest() {
		/***Given***/
		

		/***When***/
		List<MemberVo> result = memberService.getMemberAll();
		/***Then***/
		assertEquals(17, result.size());
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid = "tofan123";

		/***When***/
		int result = memberService.deleteMember(userid);
		/***Then***/
		assertEquals(1, result);
		
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		String userid = "tofan123";
		
		MemberVo mv = new MemberVo(userid, "비밀번호 수정 테스트", userid, userid, userid, userid, "12345", userid, userid);
		/***When***/
		memberService.updateMember(mv);
		/***Then***/
		MemberVo result = memberService.getMember(userid);
		assertEquals("비밀번호 수정 테스트", result.getPass());
	}
}
