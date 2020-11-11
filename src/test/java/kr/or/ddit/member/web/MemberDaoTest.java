package kr.or.ddit.member.web;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

public class MemberDaoTest extends ModelTestConfig{

	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	@Test
	public void getMemberAllTest() {
		/***Given***/

		/***When***/
		List<MemberVo> memberList = memberDao.getMemberAll();
		/***Then***/
		assertTrue(memberList.size() > 13);
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		memberDao.deleteMember(userid);
		
		List<MemberVo> memberList = memberDao.getMemberAll();
		// 원래 사이즈는 17이다.
		/***Then***/
		assertTrue(memberList.size() == 16);
	}
	
	@Test
	public void updateMemberTest() {
		/***Given***/
		String userid = "tofan123";
		MemberVo memberVo = new MemberVo(userid, "수정함", "수정함", "수정함", "수정함", "수정함", "12345", "수정함", "수정함");

		/***When***/
		memberDao.updateMember(memberVo);
		
		MemberVo result = memberDao.getMember(userid);
		/***Then***/
		assertTrue(memberVo.getPass().equals(result.getPass()));
		// 사용자가 수정할려고 한 값이 실제로 반영됐는지를 확인한다. 
		
	}
	
	
}
