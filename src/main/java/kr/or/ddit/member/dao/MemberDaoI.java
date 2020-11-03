package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.model.MemberVo;

public interface MemberDaoI {
	MemberVo getMember(SqlSession sqlSession, String userId);
	
	List<MemberVo> getMemberAll(SqlSession sqlSession);
	
	List<MemberVo> getMemberPage(SqlSession sqlSession, Map<String, Integer> page);

	int selectMemberTotalCnt(SqlSession sqlSession);
	
	int insertMember(SqlSession sqlSession , MemberVo memberVo);
	
	int deleteMember(SqlSession sqlSession, String userid);
	
	int updateMember(SqlSession sqlSession , MemberVo memberVo);
}
