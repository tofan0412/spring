package kr.or.ddit.mvc.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 사용자 정의 예외 만들기 -> 사용자에게 500 에러 대신 404 응답 코드로 응답이 가게끔 설정
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoFileException extends Exception{

}
