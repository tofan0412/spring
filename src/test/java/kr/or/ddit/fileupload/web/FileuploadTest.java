package kr.or.ddit.fileupload.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import kr.or.ddit.WebTestConfig;

public class FileuploadTest extends WebTestConfig {

	@Test
	public void fileuploadViewTest() throws Exception {
		mockMvc.perform(get("/fileupload/view")).andExpect(status().isOk())
				.andExpect(view().name("/fileupload/fileupload")).andDo(print());
	}

	@Test
	public void uploadTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("src/test/resources/kr/or/ddit/upload/sally.png");
		
//      InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("kr/or/ddit/upload/sally.png");
      
//      getClass().getResourceAsStream("src/test/resources/kr/or/ddit/upload/sally.png");
		
		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload\\sally.png");
		MockMultipartFile file = new MockMultipartFile("file", "sally.png", "image/png", fis);
		mockMvc.perform(fileUpload("/fileupload/upload")
				.file(file)
				.param("userid", "brown"))
				.andExpect(view()
				.name("/fileupload/fileupload"))
				.andExpect(status().isOk());
	}
}
