package com.wb.core.common.bean;

import java.io.File;

public class ZipFile {
   
	private File file;
	private String name;
	
	
	
	public ZipFile(File file, String name) {
		super();
		this.file = file;
		this.name = name;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
