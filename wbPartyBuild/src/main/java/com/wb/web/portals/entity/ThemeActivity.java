package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 主题活动
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_theme_activity")
public class ThemeActivity extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7495909185285128552L;
	

	private String activityName; 	 //活动名称
	private String content;		     //活动说明
	private Date startDate;			 //开始时间
	private Date endDate;			 //结束时间	

	private Set<ActivityRule> activityRule;		   	   //活动规则

	
	private Set<Manuscript> manuscripts;		   //稿件
	private Set<AwardsSetting> awardsSettings; 		   //奖项设置信息
	private Set<AwardWinningWorks> awardWinningWorks;  //获奖作品
	
	
	private Short activityType;		 //活动类型  1：月月精彩  2：城建人组稿  3：征文
	private Short status;			 //状态  0：失效     1：活跃  
	private Date createTime;   	 	 //创建时间
	private String updateBy;		 //修改者
	
	
	private Boolean isReward;	     //是否评奖
	private Boolean isOpenChunk;     //是否通过版块进行投稿
	
	public ThemeActivity() {
		super();
	}
	
	
	public ThemeActivity(Long id) {
		setId(id);
	}
	
	
	public ThemeActivity(String activityName, String content,
			Date startDate, Date endDate, Short activityType,
			Short status, Date createTime, String updateBy){
		super();
		this.activityName = activityName;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activityType = activityType;
		this.status = status;
		this.createTime = createTime;
		this.updateBy = updateBy;
	}


	@Column(name="activity_name",length=50)
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="is_reward",nullable=false)
	public Boolean getIsReward() {
		return isReward;
	}
	public void setIsReward(Boolean isReward) {
		this.isReward = isReward;
	}
	
	@Column(name="is_open_chunk",nullable=false)
	public Boolean getIsOpenChunk() {
		return isOpenChunk;
	}


	public void setIsOpenChunk(Boolean isOpenChunk) {
		this.isOpenChunk = isOpenChunk;
	}


	@OneToMany(targetEntity=AwardsSetting.class)
	@JoinColumn(name="activity_id")
	public Set<AwardsSetting> getAwardsSettings() {
		return awardsSettings;
	}
	public void setAwardsSettings(Set<AwardsSetting> awardsSettings) {
		this.awardsSettings = awardsSettings;
	}
	
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	@OneToMany(targetEntity=AwardWinningWorks.class)
	@JoinColumn(name="activity_id")
	public Set<AwardWinningWorks> getAwardWinningWorks() {
		return awardWinningWorks;
	}
	public void setAwardWinningWorks(Set<AwardWinningWorks> awardWinningWorks) {
		this.awardWinningWorks = awardWinningWorks;
	}
	
	
	@Column(name="create_time",nullable=false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	public Short getActivityType() {
		return activityType;
	}
	public void setActivityType(Short activityType) {
		this.activityType = activityType;
	}
	
	
	@Column(name="status",nullable=false)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	@OneToMany(targetEntity=Manuscript.class)
	@JoinColumn(name="activity_id")
	public Set<Manuscript> getManuscripts(){
		return manuscripts;
	}
	public void setManuscripts(Set<Manuscript> manuscripts){
		this.manuscripts = manuscripts;
	}
	
	@OneToMany(targetEntity=ActivityRule.class)
	@JoinColumn(name="activity_id")
	public Set<ActivityRule> getActivityRule() {
		return activityRule;
	}
	public void setActivityRule(Set<ActivityRule> activityRule) {
		this.activityRule = activityRule;
	}

	
	
	
}
