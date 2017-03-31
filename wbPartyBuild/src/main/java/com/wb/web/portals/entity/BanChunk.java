package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 版块
 * @author wb_java_zjr
 *
 */

@Entity
@Table(name="c_ban_chunk")
public class BanChunk extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4026790840902296706L;
	public static final short CLOSE_STATUS = 0;  //不公开
	public static final short OPEN_STATUS = 1;   //公开
	
	private String chunkName;  		//版块名称
	private String chunkMemo;  		//版块备注
	private Date createTime;   		//创建时间
	private Short status;  	   		//状态  0：不公开  1：公开
	private Boolean isLeaf;	   		//是否为叶子节点	
	private BanChunk parent;
	private ThemeActivity activity;	//所属主题活动
	private Set<BanChunk> children;
	private String updateBy;
	private Integer sortNum;	   //排序号	
	private Long linkContentId;       //关联的文章id
	
	
	@Column(name="chunk_name",length=50,nullable=false)
	public String getChunkName() {
		return chunkName;
	}
	public void setChunkName(String chunkName) {
		this.chunkName = chunkName;
	}
	
	@Column(name="chunk_memo",length=300)
	public String getChunkMemo() {
		return chunkMemo;
	}
	public void setChunkMemo(String chunkMemo) {
		this.chunkMemo = chunkMemo;
	}
	
	@Column(name="create_time",length=300)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="status",nullable=false)
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	@Column(name="is_leaf",nullable=false)
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	
	
	
	@ManyToOne(targetEntity=BanChunk.class)
	@JoinColumn(name="parent_id")
	public BanChunk getParent() {
		return parent;
	}
	public void setParent(BanChunk parent) {
		this.parent = parent;
	}
	

	@ManyToOne(targetEntity=ThemeActivity.class)
	@JoinColumn(name="activity_id")
	public ThemeActivity getActivity() {
		return activity;
	}
	public void setActivity(ThemeActivity activity) {
		this.activity = activity;
	}
	
	
	
	@OneToMany(targetEntity=BanChunk.class)
	@JoinColumn(name="parent_id")
	public Set<BanChunk> getChildren() {
		return children;
	}
	public void setChildren(Set<BanChunk> children) {
		this.children = children;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
	@Column(name="sort_num")
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
	@Column(name="link_content_id")
	public Long getLinkContentId() {
		return linkContentId;
	}
	public void setLinkContentId(Long linkContentId) {
		this.linkContentId = linkContentId;
	}
	
	public void update(String chunkName, String chunkMemo, Integer sortNum,
			Short status, String updateBy) {
		this.chunkName=chunkName;
		this.chunkMemo=chunkMemo;
		this.sortNum=sortNum;
		this.status=status;
		this.updateBy=updateBy;
		
	}
	
	
	
	
	
	
	

}
