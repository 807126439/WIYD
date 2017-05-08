package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 奖项设置
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_awards_setting")
public class AwardsSetting extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7673550241688478226L;
	
	private ThemeActivity activity;	//活动
	private String awardsName;      //奖项名称
	private String prize; 			//奖品	
	private Integer amount;			//名额
	private String memo;			//备注
	private Date createTime;  		//创建时间
	private String updateBy;		//修改者
	private Long baseFileId;		//奖品图片
	private int sortNum;			//排序
	
	public AwardsSetting() {
		super();
	}

	public AwardsSetting(Long id){
		setId(id);
	}
	public AwardsSetting(ThemeActivity activity, String awardsName,
			String prize, Integer amount, String memo, Date createTime,
			String updateBy,int sortNum) {
		super();
		this.activity = activity;
		this.awardsName = awardsName;
		this.prize = prize;
		this.amount = amount;
		this.memo = memo;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.sortNum=sortNum;
	}



	@ManyToOne(targetEntity=ThemeActivity.class)
	@JoinColumn(name="activity_id",nullable=false)
	public ThemeActivity getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivity activity) {
		this.activity = activity;
	}
	

	
	@Column(name="amount",nullable=false)
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	
	@Column(name="awards_name",length=50)
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	@Column(name="prize",length=50)
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	@Column(name="memo",length=500)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="base_file_id",nullable=false)
	public Long getBaseFileId() {
		return baseFileId;
	}


	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}
	@Column(name="sort_num",nullable=false)
	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
	
	

}
