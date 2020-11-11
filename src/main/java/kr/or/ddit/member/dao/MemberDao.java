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
	
	// Bean으로 등록하였으므로, Singleton 처리를 하지 않아도 된다. (Spring Container에서 알아서 처리 ..)
	
//	private static MemberDao dao;
	
	public MemberDao() {
		
	}
	
//	public static MemberDao getDao() {
//		if (dao == null) {
//			dao = new MemberDao();
//		}
//		return dao;
//	}
	
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
	public List<MemberVo> getMemberPage(Map<String, String> page) {
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
//		// TestCode에서 Spring이 관리하는 SqlSession에 대해 
		// 개발자가 임의로 commit을 하려고 하면 에러가 발생한다.
		return sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		// TestCode에서 Spring이 관리하는 SqlSession에 대해 
		// 개발자가 임의로 commit을 하려고 하면 에러가 발생한다.
		return sqlSession.update("member.updateMember", memberVo);
	}
}
