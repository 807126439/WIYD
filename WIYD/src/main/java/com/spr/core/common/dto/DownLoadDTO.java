package com.spr.core.common.dto;

public class DownLoadDTO {

	private String fileName;	//文件名
	private String filePath;	//文件路径
	private String fileType;	//文件类型	
	private Short fileKind;

	
	public DownLoadDTO(){}
	
	
	public DownLoadDTO(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public DownLoadDTO(String fileName, String filePath,String fileType) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
	}
	
	public DownLoadDTO(String fileName, String filePath,String fileType,Short fileKind) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.fileKind = fileKind;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Short getFileKind() {
		return fileKind;
	}

	public void setFileKind(Short fileKind) {
		this.fileKind = fileKind;
	}
	
	
}
