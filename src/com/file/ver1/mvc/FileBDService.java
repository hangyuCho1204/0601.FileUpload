package com.file.ver1.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.ver1.dto.DownloadFile;
import com.file.ver1.dto.FileDomain;
import com.file.ver1.dto.FileEntity;
import com.file.ver1.dto.FileModel;
import com.file.ver1.dto.FileUtil;

@Service
public class FileBDService {
	
	@Autowired
	private FileBDRepository fileBDRepository;
	
	public int saveFile(FileDomain fileDomain){
		//1. 파일 서버에 저장될 유일한 파일 이름 생성
		String saveFileName = FileUtil.makeSavedFileName(fileDomain.getUpFile().getOriginalFilename());
		
		
		
		//2. 파일 서버에 파일 저장
		boolean FileUploadResult = false;
		try {
			FileUploadResult = FileUtil.saveFile(saveFileName, fileDomain.getUpFile().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FileUploadResult : " + FileUploadResult);
		//3. db에 저장할 엔티티 생성
		FileEntity fileEntity = null;
		if(FileUploadResult){
			fileEntity = new FileEntity();
			
			fileEntity.setSavedfilename(saveFileName);
			fileEntity.setOriginalfilename(fileDomain.getUpFile().getOriginalFilename());
			fileEntity.setTitle(fileDomain.getTitle());
			fileEntity.setWriter(fileDomain.getWriter());
		}
		
		return fileBDRepository.insert(fileEntity);
	}
	
	public List<FileModel> getFiles(){
		
		List<FileEntity> fileEntity = fileBDRepository.selectAll();
		
		List<FileModel> fileModel = new ArrayList<FileModel>();
		
		for( FileEntity bean:fileEntity ){
			FileModel model = new FileModel();
			
			model.setId(bean.getId());
			model.setOriginalfilename(bean.getOriginalfilename());
			model.setTitle(bean.getTitle());
			model.setWriter(bean.getWriter());
			
			fileModel.add(model);
		}
		
		return fileModel;
	}
	
	public FileEntity selectById(int id){
		return fileBDRepository.selectById(id);
	}
	
	public int remove(int id){
		FileEntity fileEntity = fileBDRepository.selectById(id);
		
		boolean result = FileUtil.deleteSavedFile(fileEntity.getSavedfilename());
		System.out.println("result : "+result +"/" + "savedFileName : " + fileEntity.getSavedfilename());
		
		return fileBDRepository.remove(id);
	}

	public DownloadFile getDownloadFile(int id) {
		FileEntity fileEntity = fileBDRepository.selectById(id);
		
		File saveFile = FileUtil.getSavedFile(fileEntity.getSavedfilename());
		
		DownloadFile downloadFile = new DownloadFile();
		downloadFile.setFile(saveFile);
		downloadFile.setOriginalFileName("service oFileName : "+fileEntity.getOriginalfilename());
		
		return downloadFile;
	}
}
