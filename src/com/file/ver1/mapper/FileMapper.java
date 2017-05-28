package com.file.ver1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.file.ver1.dto.FileEntity;

public interface FileMapper {
	@Insert("insert into filebd(id, writer, title, savedfilename, originalfilename) values( #{id}, #{writer}, #{title}, #{savedfilename}, #{originalfilename} )")
	@SelectKey(statement="select filebd_seq.nextval from dual", keyProperty="id", before=true, resultType=int.class)
	public int save(FileEntity fileEntity);
	
	@Select("select * from filebd")
	public List<FileEntity> findAll();
	//디비에 데이터 넣기 전에 실제 업로드 한 파일 갯수와 DB에 들어가 있는 정보와 일치시켜야 함.
	
	@Select("select * from filebd where id = #{id}")
	public FileEntity findById(int id);
	
	@Delete("delete from filebd where id = #{id}")
	public int remove(int id);
}
