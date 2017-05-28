package com.file.ver1.dto;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;


public class FileUtil {
	//파일 서버에 저장될 유일한 파일 이름을 생성해서 리턴할 메소드 생성
	public static String makeSavedFileName(String orignalFileName){
		
		//UUID란 유일한 아이디를 만들어 주는 클래스
		UUID uid = UUID.randomUUID();
		
		String savedFile = uid.toString()+"_"+orignalFileName;
		
		return savedFile;
	}
	
	public static boolean saveFile(String saveFileName, byte[] fileData){
		//byte[]는 실제 데이터
		String uploadPath="c:\\xyz\\upload";
		File target = new File(uploadPath, saveFileName);
		
		try {
			FileCopyUtils.copy(fileData,  target);
			//실제로 데이터를 서버에 넣는 메소드
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
			//파일 저장이 안될 시 거짓을 반환
		}
		
		return true;
		//파일 저장이 될 시 참을 반환
	}
	public static File getSavedFile(String savedFileName){
		
		String uploadPath="c:\\xyz\\upload";
		File downloadFile = new File(uploadPath, savedFileName);
		//uploadPath경로로 가서 savedFileName 이름의 객체를 가지고 와라
		
		return downloadFile;
	}
	public static boolean deleteSavedFile(String savedFileName){
		
		String uploadPath="c:\\xyz\\upload";
		File a = new File(uploadPath, savedFileName);
		boolean result = a.delete();
		
		return result;
	}
}
