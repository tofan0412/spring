package kr.or.ddit.board.repository;


import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;

@Repository("boardRepository")
public class BoardRepository implements BoardRepositoryI{
	
	@Override
	public BoardVo getBoard(int boardNo) {
		// DB에서 조회를 해야 하나, 설정이 갖춰져 있지 않으므로
		// 가짜 객체 (MOCK)를 반환한다.
		if (boardNo == 1) {
			return new BoardVo(1, "첫번째 글", "내용");
		}
		else {
			return null;
		}
	}
	
}
