package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.core.spring.security.user.MyUser;

/**
 * 获奖作品信息
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_award_winning_works")
public class AwardWinningWorks extends BaseEntity implements Serializable{


	private static final long serialVersionUID = 1609917631075988934L;
	private ThemeActivity activity;	        //活动	
	private AwardsSetting awardsSetting;   //奖项	
	private Manuscript manuscript;	//文稿
	private String comment;
	private Date createTime;  		//创建时间
	private String updateBy;		//修改者
	
	public AwardWinningWorks() {
		super();
	}
	
	public AwardWinningWorks(ThemeActivity activity, AwardsSetting awardsSetting,
			Manuscript manuscript,String comment, Date createTime, String updateBy) {
		super();
		this.activity=activity;
		this.awardsSetting=awardsSetting;
		this.manuscript=manuscript;
		this.comment = comment;
		this.createTime=createTime;
		this.updateBy=updateBy;
		
		
	}
	
	@Column(name="comment",length=400)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne(targetEntity=ThemeActivity.class)
	@JoinColumn(name="activity_id",nullable=false,updatable=false)
	public ThemeActivity getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivity activity) {
		this.activity = activity;
	}
	
	@ManyToOne(targetEntity=AwardsSetting.class)
	@JoinColumn(name="awards_setting_id",nullable=false)	
	public AwardsSetting getAwardsSetting() {
		return awardsSetting;
	}
	public void setAwardsSetting(AwardsSetting awardsSetting) {
		this.awardsSetting = awardsSetting;
	}
	
	
	@ManyToOne(targetEntity=Manuscript.class)
	@JoinColumn(name="manuscript_id",nullable=false,updatable=false)
	public Manuscript getManuscript() {
		return manuscript;
	}
	
	public void setManuscript(Manuscript manuscript) {
		this.manuscript = manuscript;
	}
	
	@Column(name="create_time",nullable=false,updatable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="update_by",length=50,updatable=false)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	
	
	
	
	
	
	

}
