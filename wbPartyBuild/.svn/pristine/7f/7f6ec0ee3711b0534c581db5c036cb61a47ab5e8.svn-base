package com.wb.web.portals.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;



/**
 * @author wb_java_lzx
 *	活动规则
 */
@Entity
@Table(name="c_activity_rule")
public class ActivityRule extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7567622728261972574L;
	
	
	private ThemeActivity activity;	//活动	
	private Integer num;   			//序号
	private Boolean isMain;         //是否未主要规则
	private String content;			//内容
	
	
	
	
	public ActivityRule() {
		super();
	}
	
	public ActivityRule(ThemeActivity activity, Integer num ,Boolean isMain,
			String content) {
		super();
		this.activity = activity;
		this.num = num;
		this.isMain = isMain;
		this.content = content;
	}
	
	@ManyToOne(targetEntity=ThemeActivity.class)
	@JoinColumn(name="activity_id",nullable=false)
	public ThemeActivity getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivity activity) {
		this.activity = activity;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Boolean getIsMain() {
		return isMain;
	}
	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}
	@Column(name="content",length=2000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
