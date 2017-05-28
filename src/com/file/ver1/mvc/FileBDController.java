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
	//produces=MediaType.APPLICATION_OCTET_STREAM_VALUE = �������� ��Ʈ�� ���� ���� �� �ֵ��� �غ��� �� �ְ� �ϴ� ��ȣ���� �ǹ�
	public List<FileModel> list(Model model){
			List<FileModel> lists = fileBDService.getFiles();
			
			model.addAttribute("lists", lists);
		return null;
		//lists;
	}
	
	@RequestMapping(value="/download_v1", method=RequestMethod.GET)
	@ResponseBody
	//��Ʈ�ѷ����� Ŭ���̾�Ʈ�� ���� �����ִ� ��
	public FileSystemResource download(@RequestParam int id, HttpServletResponse response, HttpServletRequest request){
		//Ŭ���̾�Ʈ���� ���� File, �������� ���� �̸��� �ʿ�
		System.out.println("����");
		DownloadFile downloadFile = fileBDService.getDownloadFile(id);
		
		//�ٿ�ε� �����̸� �ѱ� �ȱ����� �ϱ�!
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
		
		//�������� �� ������ �а� �ؼ��ϼ� �޾Ƶ���(?)
		return new FileSystemResource(downloadFile.getFile());
		//FileSystemResource : Ŭ���̾�Ʈ�� �ٷ� ���ڴٶ�� �ǹ�
	}
	
	@RequestMapping(value="/deleteFile_v1", method=RequestMethod.GET)
	public String deleteFile(@RequestParam int id){
		
		int cnt = fileBDService.remove(id);
		
		return "redirect:fileList_v1"; 
	}
}
