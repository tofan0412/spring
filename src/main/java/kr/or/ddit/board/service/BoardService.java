package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardRepositoryI;

// <bean id="~" ... /> 
@Service("boardService") // Spring container 내에서 관리 가능 ( 서블릿은 불가능..)
public class BoardService implements BoardServiceI{
	
	// 자동으로 연결을 해주는 DI 이용
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	public BoardRepositoryI getBoardRepository() {
		return boardRepository;
	}
	
	public void setBoardRepository(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardService(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardService(){
		
	}
	
	

	@Override
	public BoardVo getBoard(int boardNo) {
		return boardRepository.getBoard(boardNo);
	}
}
