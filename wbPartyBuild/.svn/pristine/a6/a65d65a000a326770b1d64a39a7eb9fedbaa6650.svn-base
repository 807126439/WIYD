package com.wb.web.base.dto.result;

import java.util.Date;

public class SaveResult {
	private Long id;		   //保存后文件id
	private String fileName;   //文件名称
	private String pattern;    //小缩略图
	private String bigPattern; //大缩略图	
	private String srcPath;	   //原图 
	private Date dateTime;     
	private Short fileKind;		   //文件类型	
	private String downLoadPath;   //下载路径
	
	
	
	public SaveResult(Long id,String fileName, String downLoadPath) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.downLoadPath = downLoadPath;
	}

		
	
	




	public SaveResult(Long id,String fileName,String pattern, String bigPattern,String srcPath, Short fileKind) {
		super();
		this.id = id;
		this.pattern = pattern;
		this.bigPattern = bigPattern;
		this.srcPath = srcPath;
		this.fileKind = fileKind;
	}

	

	public Long getId() {
		return id;
	}
	
	
	
	
	public String getFileName() {
		return fileName;
	}


	public Date getDateTime() {
		return dateTime;
	}

	
		
	public String getBigPattern() {
		return bigPattern;
	}



	public void setBigPattern(String bigPattern) {
		this.bigPattern = bigPattern;
	}



	public String getSrcPath() {
		return srcPath;
	}



	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}



	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public Short getFileKind() {
		return fileKind;
	}



	public String getDownLoadPath() {
		return downLoadPath;
	}








	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}








	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveResult other = (SaveResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
