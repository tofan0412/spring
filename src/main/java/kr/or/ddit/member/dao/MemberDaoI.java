package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.model.MemberVo;

public interface MemberDaoI {
	MemberVo getMember(String userId);
	
	List<MemberVo> getMemberAll();
	
	List<MemberVo> getMemberPage(Map<String, String> page);

	int selectMemberTotalCnt();
	
	int insertMember(MemberVo memberVo);
	
	int deleteMember(String userid);
	
	int updateMember(MemberVo memberVo);
}
