package com.spr.core.common.bean;


public class AjaxJsonExtraParam extends AjaxJson{

	/**
	 * 存放额外简单参数
	 */
	private static final long serialVersionUID = 7793061681519973837L;
	
	
	private Object value;
	
	
	public AjaxJsonExtraParam(){
		super();
	}
	
	
	public AjaxJsonExtraParam(String info, String status, Object data,Object value) {
		super(info,status,data);
		this.value=value;
	}
	

	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	
}
