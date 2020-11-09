package kr.or.ddit.mvc.ajax.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.model.MemberVo;

@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@RequestMapping("/ajax/view")
	public String view() {
		return "ajax/view";
	}
	//consume, produce
	// consumes : 사용자가 보내는 contentType을 제한한다.
	// produces : 사용자가 응답 받기 희망(Accept)하는 mimeType을 제한 -> Jquery에서 dataType 통해 설정
	
	@RequestMapping(path="/ajax/json", consumes = {"application/xml"})
	public void json(@RequestBody MemberVo memberVo) {
		logger.debug("body : {}", memberVo);
		
		// 응답을 void로 설정하면 ? 404 ERROR 발생..
		// viewName으로 /ajax/json을 반환한다. -> viewResolver에 따라 순차적으로 생성할 view객체를 찾는다.
	}
	
	@ResponseBody
	@RequestMapping(path="/ajax/json_r", 
					consumes = {"application/json"},
					produces = {"application/json","application/xml"})
	public MemberVo json_r(@RequestBody MemberVo memberVo) {
		logger.debug("body_r : {}", memberVo);
		
		memberVo.setAlias("곰입니다 곰!");
		return memberVo;
	}
	
	
}
