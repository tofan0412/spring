package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.MyBatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

@Service("memberService")
public class MemberService implements MemberServiceI {
	
	private MemberDaoI memberDao;
	
	public MemberService(){
		memberDao = new MemberDao();
	}
	
	@Override
	public MemberVo getMember(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		MemberVo member = memberDao.getMember(sqlSession , userId);
		sqlSession.close();
		return member;
	}

	@Override
	public List<MemberVo> getMemberAll() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		List<MemberVo> memList = memberDao.getMemberAll(sqlSession);
		sqlSession.close();
		return memList;
	}

	@Override
	public Map<String, Object> getMemberPage(Map<String, Integer> page) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		Map<String, Object> map = new HashMap<>();
		List<MemberVo> memList = memberDao.getMemberPage(sqlSession, page);
		map.put("memList", memList);
		
		int totalCnt = memberDao.selectMemberTotalCnt(sqlSession);
		int pages = (int)Math.ceil((double)totalCnt / page.get("pageSize"));
		
		map.put("pages", pages);
		sqlSession.close();
		
		return map;
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = memberDao.insertMember(sqlSession, memberVo);
		
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int deleteMember(String userid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = memberDao.deleteMember(sqlSession, userid);
		
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = memberDao.updateMember(sqlSession, memberVo);
		
		sqlSession.close();
		return updateCnt;
	}
	
	


}
