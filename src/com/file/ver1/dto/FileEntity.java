package com.file.ver1.dto;

public class FileEntity {
	private int id;
	private String writer;
	private String title;
	private String savedfilename;
	private String originalfilename;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSavedfilename() {
		return savedfilename;
	}
	public void setSavedfilename(String savedfilename) {
		this.savedfilename = savedfilename;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", writer=" + writer + ", title="
				+ title + ", savedfilename=" + savedfilename
				+ ", originalfilename=" + originalfilename + "]";
	}
	
}
