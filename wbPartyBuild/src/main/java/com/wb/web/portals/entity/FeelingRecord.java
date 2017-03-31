package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.wb.core.common.entity.UUIDEntity;

/**
 * 心得体会
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_feeling_record")
public class FeelingRecord extends UUIDEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7860159202321441105L;
	
	private String ownerId;			//发布用户
	private String title;			//标题
	private String content;			//内容
	private Date createTime;		//创建时间
	
	/**
	 * 无参构造
	 */
	public FeelingRecord() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 构造方法
	 * @param ownerId
	 * @param title
	 * @param content
	 * @param createTime
	 */
	public FeelingRecord(String ownerId, String title, String content,
			Date createTime) {
		super();
		this.ownerId = ownerId;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}



	@Column(name="owner_id",length=32)
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	@Column(name="title",length=100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Type(type="text")
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	

}
