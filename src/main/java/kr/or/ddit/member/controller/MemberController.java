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
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import kr.or.ddit.member.model.JSRMemberVo;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.model.MemberVoValidator;
import kr.or.ddit.member.service.MemberServiceI;
import kr.or.ddit.model.PageVo;
import kr.or.ddit.mvc.view.ProfileImgView;


@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping("/memberRegist")
	public String memberRegist() {
		return "tiles.member.memberRegistTile";
	}
	
	@Resource
	MemberServiceI memberService;
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/Regist")
	public String Regist(@Valid MemberVo memberVo, BindingResult br,@RequestPart("file") MultipartFile file, Model model) {
//	public String Regist(@Valid JSRMemberVo memberVo, BindingResult br,@RequestPart("file") MultipartFile file, Model model) {
		// 검증
		// new MemberVoValidator().validate(memberVo, br);
		
		if (br.hasErrors()) {
			// 검증을 통과하지 못했으므로 사용자 등록 화면으로 이동한다. 
			return "tiles.member.memberRegistTile";
		}
		
		memberVo.setRealFilename(file.getOriginalFilename());
		memberVo.setFilename("d:\\upload\\"+file.getOriginalFilename());
		
		// 파일 업로드하기..
		File uploadFile = new File("d:\\upload\\" + file.getOriginalFilename());
		
		// if(file.getSize() > 0) 으로 사용자가 파일을 등록했는지, 안했는지를 구별할 수 있다. 
		
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
		
		return "tiles.member.memberListTile";
	}
	
	
	// 최초 페이지 진입시
	@RequestMapping("/getMemberList")
	public String getMemberList(Model model) {
		// 다음과 같이 파라미터를 받으면, getMemberList, getMemberList_p로 메서드를 나눌 필요가 없다.
		// @RequestParam("page", required=false ,defaultValue=1) int page 
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
		
		//return "member/memberListContent";
		return "tiles.member.memberListTile";
	}
	
	
	
	
	@RequestMapping(path="/listAjaxPage")
	public String listAjaxPage() {
		return "tiles.member.listAjaxPage";
	}
	
	// 페이지요청과 다르게 page, pageSize 파라미터가 반드시 존재한다는 가정으로 작성한다. 
	@RequestMapping("/listAjax")
	public String listAjax(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);
		Map<String, Integer> page = new HashMap<>();
		// 필요한 페이지의 갯수
		page.put("page", pageVo.getPage());
		// 한 페이지 당 출력할 글의 갯수
		page.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> map = memberService.getMemberPage(page);
		model.addAttribute("memList", map.get("memList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "jsonView";
	}
	
	
	@RequestMapping("/listAjaxHTML")
	public String listAjaxHTML(PageVo pageVo, Model model) {
		Map<String, Integer> page = new HashMap<>();
		// 필요한 페이지의 갯수
		page.put("page", pageVo.getPage());
		// 한 페이지 당 출력할 글의 갯수
		page.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> map = memberService.getMemberPage(page);
		model.addAttribute("memList", map.get("memList"));
		model.addAttribute("pages", map.get("pages"));
		
		// 응답을 html => jsp로 생성
		return "member/listAjaxHTML";
	}
	
	
	// 사용자 이름을 클릭했을 때 사용자 정보를 출력 ..
	@RequestMapping(path="/showMemberInfo")
	public String showMemberInfo(@RequestParam("userid") String userid, Model model) {
		
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "tiles.member.memberTile";
	}
	
	@RequestMapping(path="/MemberInfoAjax")
	public String memberInfoAjax(String userid, Model model) {
		model.addAttribute("userid", userid);
		return "tiles.member.memberAjax";
	}
	
	@RequestMapping("showMemberInfoAjax")
	public String showMemberInfoAjax(String userid, Model model) {
		logger.debug("userid : {}", userid);
		
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "member/memberAjaxHTML";
	}
	
	
	@RequestMapping("/showMemberInfo_u")
	public String showMemberInfo_u(@RequestParam("userid") String userid, Model model) {
		
		MemberVo memberVo = memberService.getMember(userid);
		
		model.addAttribute("memberVo", memberVo);
		
		return "tiles.member.memberUpdateTile";
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
			return "tiles.member.memberTile";
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
	
	@RequestMapping("/profileImgView")
	public String/*View*/ profileImgView(@RequestParam("userid") String userid, Model model) 
				throws ServletException, IOException{
		// 응답으로 생성하려고 하는 것 : Img 파일을 읽어서 output stream 객체에 쓰는 것
		// 
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("filepath", memberVo.getFilename());
		
//		return new ProfileImgView();
		return "profileImgView";
	}
	
	@RequestMapping("profileDownload")
	public String profileDownload(@RequestParam("userid") String userid, Model model)
				throws ServletException, IOException{
		MemberVo memberVo = memberService.getMember(userid);
		model.addAttribute("realFilename", memberVo.getRealFilename());
		model.addAttribute("filepath", memberVo.getFilename());
		return "profileImgDownloadView";
	}
	
	
}
