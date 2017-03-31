package com.wb.web.portals.dto.column;


public class IndexItem {

	private ColumnHead top;
	private Object innner;
	private Boolean isInner;  //1:属性 innner为List<IndexItem>  0为List<ShowContentDTO>
	
	
	
	public IndexItem(ColumnHead top, Object innner,Boolean isInner) {
		super();
		this.top = top;
		this.innner = innner;
		this.isInner = isInner;
	}



	public ColumnHead getTop() {
		return top;
	}



	public void setTop(ColumnHead top) {
		this.top = top;
	}



	public Object getInnner() {
		return innner;
	}



	public void setInnner(Object innner) {
		this.innner = innner;
	}



	public Boolean getIsInner() {
		return isInner;
	}



	public void setIsInner(Boolean isInner) {
		this.isInner = isInner;
	}
	
	
	
	
	
	
	
	
	
	
	
}
