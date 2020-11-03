package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MyBatisUtil;
import kr.or.ddit.member.model.MemberVo;

public class MemberDao implements MemberDaoI{
	private static MemberDao dao;
	
	public MemberDao() {
		
	}
	
	public static MemberDao getDao() {
		if (dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	@Override
	public MemberVo getMember(SqlSession sqlSession , String userId) {
		MemberVo memberVo = sqlSession.selectOne("member.getMember", userId);
		return memberVo;
	}

	@Override
	public List<MemberVo> getMemberAll(SqlSession sqlSession) {
		List<MemberVo> memlist = sqlSession.selectList("member.getMemberAll");
		return memlist;
	}

	@Override
	public List<MemberVo> getMemberPage(SqlSession sqlSession, Map<String, Integer> page) {
		return sqlSession.selectList("member.getMemberPage", page);
	}

	@Override
	public int selectMemberTotalCnt(SqlSession sqlSession) {
		return sqlSession.selectOne("member.selectMemberTotalCnt");
	}

	@Override
	public int insertMember(SqlSession sqlSession, MemberVo memberVo) {
		int insertCnt = 0;
		
		try {
			insertCnt = sqlSession.insert("member.insertMember", memberVo);
		} catch (Exception e) {
			// 아무것도 입력하지 않아도 다음 코드가 순차적으로 실행된다.
		}
		
		if (insertCnt == 1 ) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		return insertCnt;
	}

	@Override
	public int deleteMember(SqlSession sqlSession, String userid) {
		int deleteCnt = sqlSession.delete("member.deleteMember", userid);
		
		if(deleteCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return deleteCnt;
	}

	@Override
	public int updateMember(SqlSession sqlSession, MemberVo memberVo) {
		int updateCnt = sqlSession.update("member.updateMember", memberVo);
		
		if(updateCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return updateCnt;
	}
}
