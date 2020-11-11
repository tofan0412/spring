package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.MyBatisUtil;
import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVo;

@Service("memberService")
public class MemberService implements MemberServiceI {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	public MemberService(){
//		memberDao = new MemberDao();
	}
	
	@Override
	public MemberVo getMember(String userId) {
		MemberVo member = memberDao.getMember(userId);
		return member;
	}

	@Override
	public List<MemberVo> getMemberAll() {
		
		List<MemberVo> memList = memberDao.getMemberAll();
		return memList;
	}

	@Override
	public Map<String, Object> getMemberPage(Map<String, String> page) {
		
		Map<String, Object> map = new HashMap<>();
		List<MemberVo> memList = memberDao.getMemberPage(page);
		map.put("memList", memList);
		
		int totalCnt = memberDao.selectMemberTotalCnt();
		int pages = (int)Math.ceil((double)totalCnt / Integer.parseInt(page.get("pageSize")));
		
		map.put("pages", pages);
		
		return map;
	}

	@Override
	public int insertMember(MemberVo memberVo) {
		/*
		logger.debug("첫번째 insert 시작전");
		memberDao.insertMember(memberVo);
		logger.debug("첫번째 insert 시작후");
		
		*선언적 transaction의 확인
		
		첫번째 쿼리는 정상적으로 실행되지만 두번째 쿼리에서 동일한 데이터를 입력하여 PK 제약 조건에 의해
		SQL 실행 실패
		첫번째 쿼리는 성공했지만 트랜잭션 설정을 service 레벨에 설정을 하였기 때문에
		서비스 메서드에서 실행된 모드 쿼리를 rollback 처리한다.
		
		logger.debug("두번째 insert 시작전");
		memberDao.insertMember(memberVo);
		logger.debug("두번째 insert 시작후");
		*/
		return memberDao.insertMember(memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		int deleteCnt = memberDao.deleteMember(userid);
		return deleteCnt;
	}

	@Override
	public int updateMember(MemberVo memberVo) {
		int updateCnt = memberDao.updateMember(memberVo);
		
		return updateCnt;
	}
}
