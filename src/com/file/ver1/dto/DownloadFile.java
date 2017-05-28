package com.file.ver1.dto;

import java.io.File;

public class DownloadFile {
	private File file;
	private String originalFileName;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	@Override
	public String toString() {
		return "DownloadFile [file=" + file + ", originalFileName="
				+ originalFileName + "]";
	}
	
}
