package com.spr.web.file.dto.baseFile;


public class SaveResult {
	private Long id;		  	  //保存后文件id	
	private String viewPattern;   	  //小缩略图
	private String viewBigPattern;    //大缩略图	
	private Short fileKind;		   //文件类型	
	private String downLoadPath;   //下载路径
	private String viewPath;	   //预览路径
	private String documentName;	//文件名

	
	public SaveResult(Long id){
		this.id = id;
	}
	
	public SaveResult(Long id, String viewPattern, String viewBigPattern,
			Short fileKind, String downLoadPath, String viewPath) {
		super();
		this.id = id;
		this.viewPattern = viewPattern;
		this.viewBigPattern = viewBigPattern;
		this.fileKind = fileKind;
		this.downLoadPath = downLoadPath;
		this.viewPath = viewPath;

	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getViewPattern() {
		return viewPattern;
	}


	public void setViewPattern(String viewPattern) {
		this.viewPattern = viewPattern;
	}


	public String getViewBigPattern() {
		return viewBigPattern;
	}


	public void setViewBigPattern(String viewBigPattern) {
		this.viewBigPattern = viewBigPattern;
	}


	public Short getFileKind() {
		return fileKind;
	}


	public void setFileKind(Short fileKind) {
		this.fileKind = fileKind;
	}


	public String getDownLoadPath() {
		return downLoadPath;
	}


	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}


	public String getViewPath() {
		return viewPath;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	

	

	
	
	
	



	
	
	
}
