package kr.or.ddit.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.board.repository.BoardRepositoryI;

public class Person {
	private int age;
	private BoardRepositoryI boardRepository;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@DateTimeFormat(pattern="MM-dd-yyyy") 와 같이 형식에 대해 자유롭다.
	private Date birthdate;
	
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public BoardRepositoryI getBoardRepository() {
		return boardRepository;
	}
	public void setBoardRepository(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
}
