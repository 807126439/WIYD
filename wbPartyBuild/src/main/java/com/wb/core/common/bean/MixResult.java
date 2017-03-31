package com.wb.core.common.bean;

public class MixResult {
   
	private Object resultOne;
	private Object resultTwo;
	
	
	
	public MixResult(Object resultOne, Object resultTwo) {
		super();
		this.resultOne = resultOne;
		this.resultTwo = resultTwo;
	}
	
	public Object getResultOne() {
		return resultOne;
	}
	public void setResultOne(Object resultOne) {
		this.resultOne = resultOne;
	}
	public Object getResultTwo() {
		return resultTwo;
	}
	public void setResultTwo(Object resultTwo) {
		this.resultTwo = resultTwo;
	}
	
	
	
	
}
