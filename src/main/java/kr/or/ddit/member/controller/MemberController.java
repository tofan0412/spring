package kr.or.ddit.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberServiceI;


@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping("/memberRegist")
	public String memberRegist() {
		return "/member/memberRegist";
	}
	
	@Resource
	MemberServiceI memberService;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/Regist")
	public String Regist(MemberVo memberVo,
						@RequestPart("file") MultipartFile file, Model model) {
		memberVo.setRealFilename(file.getOriginalFilename());
		memberVo.setFilename("d:\\upload\\"+file.getOriginalFilename());
		
		// 파일 업로드하기..
		File uploadFile = new File("d:\\upload\\" + file.getOriginalFilename());
		
		// 파일 변경 사항을 DB에 기재한다. 
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		int insertCnt = memberService.insertMember(memberVo);
		if (insertCnt > 0) {
			// 테스트 코드를 위해서
			model.addAttribute("memberVo", memberVo);
			return "redirect:/member/getMemberList";
		}
		else {
			return "main";
		}
	}
	
	@RequestMapping("/getMemberList_P")
	public String getMemberList(@RequestParam("page") int page_str, 
								@RequestParam("pageSize") int pageSize_str,
								Model model) {
		Map<String, Integer> page = new HashMap<>();
		
		int pagenm = page_str;
		int pageSize = pageSize_str;
		
		model.addAttribute("page", pagenm);
		model.addAttribute("pageSize" , pageSize);
		
		// 필요한 페이지의 갯수
		page.put("page", pagenm);
		// 한 페이지 당 출력할 글의 갯수
		page.put("pageSize", pageSize);
		
		Map<String, Object> map = memberService.getMemberPage(page);
		
		model.addAttribute("memList", map.get("memList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "/member/memberList";
	}
	
	
	// 최초 페이지 진입시
	@RequestMapping("/getMemberList")
	public String getMemberList(Model model) {
		Map<String, Integer> page = new HashMap<>();
		
		int pagenm = 1;
		int pageSize = 5;
		
		model.addAttribute("page", pagenm);
		model.addAttribute("pageSize" , pageSize);
		
		// 필요한 페이지의 갯수
		page.put("page", pagenm);
		// 한 페이지 당 출력할 글의 갯수
		page.put("pageSize", pageSize);
		
		Map<String, Object> map = memberService.getMemberPage(page);
		
		model.addAttribute("memList", map.get("memList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "/member/memberList";
	}
	
	// 사용자 이름을 클릭했을 때 사용자 정보를 출력 ..
	@RequestMapping(path="/showMemberInfo")
	public String showMemberInfo(@RequestParam("userid") String userid, Model model) {
		
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "/member/member";
	}
	
	@RequestMapping("/showMemberInfo_u")
	public String showMemberInfo_u(@RequestParam("userid") String userid, Model model) {
		
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "/member/memberUpdate";
	}
	
	@RequestMapping("/memberUpdate")
	public String memberUpdate(MemberVo memberVo,
					@RequestPart("file") MultipartFile file,
					Model model) {
		
		logger.debug("memberVo : {}", memberVo);
		logger.debug("파일 상세 정보 : 이름 {} 파일이름 {} 용량 {}",
				file.getName(), file.getOriginalFilename(), file.getSize());
		
		// 사용자가 사진을 변경하지 않았을 경우..
		if (file.getOriginalFilename().length() < 1) {
			logger.debug("사용자가 수정할 사진을 업로드 하지 않았음..");
		}
		// 사용자가 사진을 변경한 경우 ...
		else {
			File uploadFile = new File("d:\\upload\\" + file.getOriginalFilename());
			
			// 파일 변경 사항을 DB에 기재한다. 
			memberVo.setFilename("d:\\upload\\"+file.getOriginalFilename());
			memberVo.setRealFilename(file.getOriginalFilename());
			try {
				file.transferTo(uploadFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = memberService.updateMember(memberVo);
		logger.debug("변경후 사용자 정보 : {}", memberVo);
		
		if (updateCnt > 0) {
			model.addAttribute("memberVo", memberVo);
			return "/member/member";
		}
		else {
			return "/member/getMemberList";
		}
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(@RequestParam("userid") String userid, 
			HttpServletResponse response) throws ServletException, IOException{
		
		// db에서 사용자 filename 확인
		MemberVo memberVo = memberService.getMember(userid);
	
		// 경로 확인 후 파일 입출력을 통해 응답 생성
		// 파일 읽기
		// 응답 생성
		memberVo.getFilename();	// 파일 경로
		FileInputStream fis = new FileInputStream(memberVo.getFilename());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		while( fis.read(buffer) != -1) {	// 읽을 수 있는게 존재하는 동안..
			sos.write(buffer);
		}
		fis.close();
		sos.flush();	// 버퍼에 남아있는걸 전송
		sos.close();
	}
	
	@RequestMapping("profileDownload")
	public void profileDownload(@RequestParam("userid") String userid,
					HttpServletResponse response) throws ServletException, IOException{
		MemberVo memberVo = memberService.getMember(userid);

		// response의 content-type 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"\""+ memberVo.getRealFilename() + "\"");
		response.setContentType("application/octet-stream");
		
		// 경로 확인 후 파일 입출력을 통해 응답 생성
		// 파일 읽기
		// 응답 생성
		memberVo.getFilename();	// 파일 경로
		FileInputStream fis = new FileInputStream(memberVo.getFilename());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		while( fis.read(buffer) != -1) {	// 읽을 수 있는게 존재하는 동안..
			sos.write(buffer);
		}
		fis.close();
		sos.flush();	// 버퍼에 남아있는걸 전송
		sos.close();
	}
}
