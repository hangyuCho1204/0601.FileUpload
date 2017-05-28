package com.file.ver1.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.file.ver1.dto.FileEntity;
import com.file.ver1.mapper.FileMapper;

@Repository
public class FileBDRepository {
	
	@Autowired
	private FileMapper mapper;
	
	public int insert(FileEntity fileEntity){
		return mapper.save(fileEntity);
	}
	
	public List<FileEntity> selectAll(){
		return mapper.findAll();
	}
	
	public FileEntity selectById(int id){
		
		return mapper.findById(id);
	}
	public int remove(int id){
		return mapper.remove(id);
	}
}
