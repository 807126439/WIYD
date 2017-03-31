package com.wb.core.utils.pojo;

import java.io.Serializable;

public class DataTableHead implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7063453471409649412L;
	
	private String sTitle;
	private String mDataProp;
	private Boolean bSortable;
	private String mRender;
	
	public DataTableHead(){}
	
	public DataTableHead(String sTitle, String mDataProp, Boolean bSortable) {
		super();
		this.sTitle = sTitle;
		this.mDataProp = mDataProp;
		this.bSortable = bSortable;

	}
	
	
	
	public DataTableHead(String sTitle, String mDataProp, Boolean bSortable,
			String mRender) {
		super();
		this.sTitle = sTitle;
		this.mDataProp = mDataProp;
		this.bSortable = bSortable;
		this.mRender = mRender;
	}
	
	
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getmDataProp() {
		return mDataProp;
	}
	public void setmDataProp(String mDataProp) {
		this.mDataProp = mDataProp;
	}
	public Boolean getbSortable() {
		return bSortable;
	}
	public void setbSortable(Boolean bSortable) {
		this.bSortable = bSortable;
	}
	public String getmRender() {
		return mRender;
	}
	public void setmRender(String mRender) {
		this.mRender = mRender;
	}
	
	
	
	

}
