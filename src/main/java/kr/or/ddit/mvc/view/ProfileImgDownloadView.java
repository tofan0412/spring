package kr.or.ddit.mvc.view;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.member.model.MemberVo;

public class ProfileImgDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// response의 content-type 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"\"" + model.get("realFilename") + "\"");
		response.setContentType("application/octet-stream");

		// 경로 확인 후 파일 입출력을 통해 응답 생성
		// 파일 읽기
		// 응답 생성
		
		FileInputStream fis = new FileInputStream((String)model.get("filepath"));
		ServletOutputStream sos = response.getOutputStream();

		byte[] buffer = new byte[512];

		while (fis.read(buffer) != -1) { // 읽을 수 있는게 존재하는 동안..
			sos.write(buffer);
		}
		fis.close();
		sos.flush(); // 버퍼에 남아있는걸 전송
		sos.close();
	}
}
