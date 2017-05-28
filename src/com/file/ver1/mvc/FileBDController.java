package com.file.ver1.mvc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.ver1.dto.DownloadFile;
import com.file.ver1.dto.FileDomain;
import com.file.ver1.dto.FileModel;

@Controller
public class FileBDController {
	
	@Autowired
	private FileBDService fileBDService;
	
	@RequestMapping(value="/fileUpload_v1", method=RequestMethod.GET)
	public String uploadReady(){
		return "upload_v1";
	}
	
	@RequestMapping(value="/fileUpload_v1", method=RequestMethod.POST)
	public String uploadFile(@ModelAttribute FileDomain fileDomain){
		
		fileBDService.saveFile(fileDomain);
		System.out.println(fileDomain);
		return "upload_v1";
	}
	//redirect:
	
	@RequestMapping(value="/fileList_v1", method=RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	//produces=MediaType.APPLICATION_OCTET_STREAM_VALUE = 브라우저가 스트림 값을 받을 수 있도록 준비할 수 있게 하는 신호같은 의미
	public List<FileModel> list(Model model){
			List<FileModel> lists = fileBDService.getFiles();
			
			model.addAttribute("lists", lists);
		return null;
		//lists;
	}
	
	@RequestMapping(value="/download_v1", method=RequestMethod.GET)
	@ResponseBody
	//컨트롤러에서 클라이언트에 직접 보내주는 것
	public FileSystemResource download(@RequestParam int id, HttpServletResponse response, HttpServletRequest request){
		//클라이언트에게 보낼 File, 오리지널 파일 이름이 필요
		System.out.println("도착");
		DownloadFile downloadFile = fileBDService.getDownloadFile(id);
		
		//다운로드 파일이름 한글 안깨지게 하기!
		String downName = null;
		String browser = request.getHeader("User-Agent");
		
		if(browser.contains("Chrome") || browser.contains("MISE") || browser.contains("Trident")){
			try {
				downName = URLEncoder.encode(downloadFile.getOriginalFileName(), "UTF-8").replaceAll("\\\\", "%20");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				downName = new String(downloadFile.getOriginalFileName().getBytes("UTF-8"), "ISO-8859-1").replaceAll("\\\\", "_");
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.setHeader("content-Disposition", "attachment; filename="+downloadFile.getOriginalFileName()+";");
		
		//브라우저가 이 파일을 읽고 해석하서 받아들임(?)
		return new FileSystemResource(downloadFile.getFile());
		//FileSystemResource : 클라이언트로 바로 가겠다라는 의미
	}
	
	@RequestMapping(value="/deleteFile_v1", method=RequestMethod.GET)
	public String deleteFile(@RequestParam int id){
		
		int cnt = fileBDService.remove(id);
		
		return "redirect:fileList_v1"; 
	}
}
