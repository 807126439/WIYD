package com.spr.web.Investment.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UrlDTO {
	
	private String fundId;
	
	private Integer page;
	
	private Integer per;
	
	private Date sdate;
	
	private Date edate;
	
	
	
	

	public UrlDTO(String fundId, Integer page, Integer per, Date sdate,
			Date edate) {
		super();
		this.fundId = fundId;
		this.page = page;
		this.per = per;
		this.sdate = sdate;
		this.edate = edate;
	}
	
	public String getUrl(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String start ="";
		String end ="";
		if(sdate!=null){
			start = formatter.format(sdate);
		}
		if(edate!=null){
			end = formatter.format(edate);
		}
		return "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="+fundId+"&page="+page+"&per="+per+"&sdate="+start+"&edate="+end;
		
	}
	

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPer() {
		return per;
	}

	public void setPer(Integer per) {
		this.per = per;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}
	
	
	
	
	

}
