package com.wb.web.portals.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;

/**
 * 栏目
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="cy_columnmu")
public class ColumnMu extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036118263528927436L;
	public static final short SINGAL_PAGE_TYPE = 1;
	public static final short NEWS_LIST_TYPE = 2;
	public static final short IMG_LIST_TYPE = 3;
	public static final short HYPER_lINK_TYPE = 4;
	public static final short VIDEO_LIST_TYPE = 5;
	public static final short MAGAZINE_TYPE = 6;

	public static final int MAX_CHILD_NUM = 3;
		
	public static final short NORMAL_SHOW_TYPE = 0;
	public static final short TAB_SHOW_TYPE = 1;
	public static final short LIST_SHOW_TYPE = 2;
	
	
	private String title;			//标题
	private String alias;	    	//别名
	private String linkUrl;			//超链接地址
	private Short typeId;			//类型	1单页面2新闻列表3图片排列4超链接5:视频列表
	private Integer sortNum;		//排序号
	private Short level;			//层级 （从1开始）
	private Boolean isIndexNav; 	//是否在首页导航行中显示
	private Boolean isIndex;		//是否置于首页
	private Integer indexNum;	 	//首页排序号	
	private Short showType; 		//展示类型  0：本栏目   1： 连级展示子栏目内容(选项卡方式)  2：父级展示子栏目内容
	private Boolean isIgnorePre;	//忽略上一级栏目显示类型 
	private Short maxContentSize;   //置于首页显示的文章最大数量
	private ColumnMu parent;			//父栏目
	private Set<ColumnMu> children;     //子栏目
	private Set<Content> contents;		//文章
	private String updateBy;			
	
	private String allowUserId;	   //允许操作的用户id（多个部门以逗号(英文)隔开）
	
	public ColumnMu(){}
	
	public ColumnMu(Long id){
		setId(id);
	}
	
	
	public ColumnMu(String title, String alias, String linkUrl, Short typeId,Integer sortNum, 
			Short level,Boolean isIndexNav, Boolean isIndex, Integer indexNum,Boolean isIgnorePre,
			Short maxContentSize, ColumnMu parent, String updateBy) {
		super();
		this.title = title;
		this.alias = alias;
		this.linkUrl = linkUrl;
		this.typeId = typeId;
		this.sortNum = sortNum;
		this.level = level;
		this.isIndexNav = isIndexNav;
		this.isIndex = isIndex;
		this.indexNum = indexNum;
		this.isIgnorePre = isIgnorePre;
		this.maxContentSize = maxContentSize;
		this.showType = NORMAL_SHOW_TYPE;
		this.parent = parent;
		this.updateBy = updateBy;
	}
	
	
	
	@Column(name="title",length=80)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	@Column(name="alias",length=100)
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name="link_url",length=500)
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
		
	@Column(name="type_id",nullable=false)	
	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}
	
	
	@Column(name="sort_num",nullable=false)	
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Column(name="level",nullable=false)
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	

	@Column(name="is_index_nav",nullable=false)
	public Boolean getIsIndexNav() {
		return isIndexNav;
	}

	public void setIsIndexNav(Boolean isIndexNav) {
		this.isIndexNav = isIndexNav;
	}

	@Column(name="is_index",nullable=false)
	public Boolean getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}
	
	@Column(name="index_num")
	public Integer getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}
	
	@Column(name="show_type",nullable=false)
	public Short getShowType() {
		return showType;
	}

	public void setShowType(Short showType) {
		this.showType = showType;
	}
	

	@Column(name="is_ignore_pre",nullable=false)
	public Boolean getIsIgnorePre() {
		return isIgnorePre;
	}

	public void setIsIgnorePre(Boolean isIgnorePre) {
		this.isIgnorePre = isIgnorePre;
	}

	@Column(name="max_content_size",nullable=false)
	public Short getMaxContentSize() {
		return maxContentSize;
	}

	
	public void setMaxContentSize(Short maxContentSize) {
		this.maxContentSize = maxContentSize;
	}

	@ManyToOne(targetEntity=ColumnMu.class)
	@JoinColumn(name="parent_id")
	public ColumnMu getParent() {
		return parent;
	}
	public void setParent(ColumnMu parent) {
		this.parent = parent;
	}
	
	@OneToMany(targetEntity=ColumnMu.class,cascade={CascadeType.REMOVE})
	@JoinColumn(name="parent_id")
	public Set<ColumnMu> getChildren() {
		return children;
	}
	public void setChildren(Set<ColumnMu> children) {
		this.children = children;
	}
	
	@OneToMany(targetEntity=Content.class)
	@JoinColumn(name="columnmu_id")
	public Set<Content> getContents() {
		return contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

	@Column(name="update_by")
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}



	@Column(name="allow_user_id",length=1000)
	public String getAllowUserId() {
		return allowUserId;
	}

	public void setAllowUserId(String allowUserId) {
		this.allowUserId = allowUserId;
	}
	
	
	
	
	
}
