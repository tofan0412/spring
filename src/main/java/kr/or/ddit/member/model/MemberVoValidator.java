package kr.or.ddit.member.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberVoValidator implements Validator{

	// 검증하려고 하는 객체가 MemberVoValidator에서 검증이 가능한
	// 객체인 지를 boolean으로 반환한다.
	@Override
	public boolean supports(Class<?> clazz) {
		// MemberVo와 관련이 있는지 없는지를 확인한다. 
		return MemberVo.class.isAssignableFrom(clazz);
	}

	// 검증 로직을 작성
	// Object target : command 객체를 의미한다. (Validator 바로 앞에 선언되어 있는 검증할 객체..)
	@Override
	public void validate(Object target, Errors errors) {
		MemberVo memberVo = (MemberVo) target;
		
		// usernm에 대해 검증해보자. usernm 값이 null 이거나 empty 문자열이면 안된다.
		if (memberVo.getUsernm() == null || memberVo.getUsernm().equals("")) {
			errors.rejectValue("usernm", "required");
		}
		
	}

}
