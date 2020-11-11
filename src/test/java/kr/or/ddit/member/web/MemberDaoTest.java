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

}
