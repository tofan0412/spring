package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.model.MemberVo;

@Repository("memberDao")
public class MemberDao implements MemberDaoI{
	
	@Resource(name="SqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
	public MemberVo getMember(String userId) {
		MemberVo memberVo = sqlSession.selectOne("member.getMember", userId);
		return memberVo;
	}

	@Override
	public List<MemberVo> getMemberAll() {
		List<MemberVo> memlist = sqlSession.selectList("member.getMemberAll");
		return memlist;
	}

	@Override
	public List<MemberVo> getMemberPage(Map<String, Integer> page) {
		return sqlSession.selectList("member.getMemberPage", page);
	}

	@Override
	public int selectMemberTotalCnt() {
		return sqlSession.selectOne("member.selectMemberTotalCnt");
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		return sqlSession.insert("member.insertMember", memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		int deleteCnt = sqlSession.delete("member.deleteMember", userid);
		
		if(deleteCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		int updateCnt = sqlSession.update("member.updateMember", memberVo);
		
		if(updateCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return updateCnt;
	}
}
