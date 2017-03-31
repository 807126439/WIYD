package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 意见反馈
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_opinion_feedback")
public class OpinionFeedback extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -5533897266934749388L;
	public static final short Reasonable_Advise_TYPE = 1;
	public static final short Wisdom_Advise_TYPE = 2;
	
	private String content;  		//意见内容
	private Date feedbackTime; 		//反馈时间
	private String ipAddress;  		//ip地址
	private String feedbackUser;	//反馈用户
	private Short typeId;			//类型   1：合理化建议征集 2：对智慧党建版块意见
	
	//(针对合理化建议征集)
	private String opinionNum;   //编号
	private String chineseName;  //姓名
	private String department;   //科室
	private String post;		 //岗位
	private String theme;		 //申报主题 
	private String forecast;	 //效益预测
	 
	
	
	public OpinionFeedback(){}
	
	
	
	
	/**
	 * 对智慧版块的建议构造函数
	 * @param content
	 * @param feedbackTime
	 * @param ipAddress
	 * @param feedbackUser
	 * @param typeId
	 * @param theme
	 */
	public OpinionFeedback(String content, Date feedbackTime, String ipAddress,
			String feedbackUser, Short typeId, String theme) {
		super();
		this.content = content;
		this.feedbackTime = feedbackTime;
		this.ipAddress = ipAddress;
		this.feedbackUser = feedbackUser;
		this.typeId = typeId;
		this.theme = theme;
	}



	/**
	 * 合理化建议构造函数
	 * @param content
	 * @param feedbackTime
	 * @param ipAddress
	 * @param feedbackUser
	 * @param typeId
	 * @param chineseName
	 * @param department
	 * @param post
	 * @param theme
	 * @param forecast
	 */
	public OpinionFeedback(String content, Date feedbackTime, String ipAddress,
			String feedbackUser, Short typeId, String chineseName,
			String department, String post, String theme, String forecast) {
		super();
		this.content = content;
		this.feedbackTime = feedbackTime;
		this.ipAddress = ipAddress;
		this.feedbackUser = feedbackUser;
		this.typeId = typeId;
		this.chineseName = chineseName;
		this.department = department;
		this.post = post;
		this.theme = theme;
		this.forecast = forecast;
	}
	
	
	
	@Column(name="content",length=500)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="feedback_time",nullable=false)
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name="feedback_user",length=50)
	public String getFeedbackUser() {
		return feedbackUser;
	}
	public void setFeedbackUser(String feedbackUser) {
		this.feedbackUser = feedbackUser;
	}
	
	@Column(name="type_id",nullable=false)
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}
	
	@Column(name="opinion_num",length=50)
	public String getOpinionNum() {
		return opinionNum;
	}
	public void setOpinionNum(String opinionNum) {
		this.opinionNum = opinionNum;
	}
	
	@Column(name="chinese_name",length=10)
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	
	@Column(name="department",length=30)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name="post",length=20)
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	@Column(name="theme",length=200)
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	@Column(name="forecast",length=200)
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	
	
}
