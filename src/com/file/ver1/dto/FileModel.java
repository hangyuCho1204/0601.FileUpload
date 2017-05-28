package com.file.ver1.dto;

public class FileModel {
	private int id;
	private String writer;
	private String title;
	private String originalfilename;
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
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
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", writer=" + writer + ", title="
				+ title + ", originalfilename=" + originalfilename + "]";
	}
	
}
