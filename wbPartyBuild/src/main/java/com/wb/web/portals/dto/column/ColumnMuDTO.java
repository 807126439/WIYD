package com.wb.web.portals.dto.column;

import org.apache.commons.lang3.StringUtils;




public class ColumnMuDTO {
	private Long id;
	private String title;		 //标题
	private String title2;		 //标题
	private String alias;	     //别名
	private String linkUrl;		 //超链接地址
	private Short typeId;		 //类型	1单页面 2新闻列表3 图片排列 4超链接
	private String typeText;
	private Integer sortNum;	 //排序号
	private Short level;		 //层级
	private Boolean isIndexNav;
	private Boolean isIndex;	 //是否置于首页
	private Integer indexNum;	 //首页排序号	
	private Boolean isIgnorePre; 
	private Short showType; 
	private String showTypeText;
	private Short maxContentSize;
	private Long parentId;		 //父栏目
	
	private String allowOrgId;
	private String allowUserId;		//有修改权限的用户ID，空则没有限制
	private Boolean isAllowEdit = false; //是否可修改
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getLinkUrl() {
		return StringUtils.isBlank(linkUrl) ? null:this.linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}	
	public Boolean getIsIndexNav() {
		return isIndexNav == null? false:isIndexNav;
	}
	public void setIsIndexNav(Boolean isIndexNav) {
		this.isIndexNav = isIndexNav;
	}
	public Boolean getIsIndex() {
		return isIndex == null? false:isIndex;
	}
	public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}
	public Integer getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public Boolean getIsIgnorePre() {
		return isIgnorePre == null ? false:isIgnorePre;
	}
	public void setIsIgnorePre(Boolean isIgnorePre) {
		this.isIgnorePre = isIgnorePre;
	}
	public Short getShowType() {
		return showType;
	}
	public void setShowType(Short showType) {
		this.showType = showType;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
		
	public String getShowTypeText() {
		return showTypeText;
	}
	public void setShowTypeText(String showTypeText) {
		this.showTypeText = showTypeText;
	}
	public Short getMaxContentSize() {
		return maxContentSize;
	}
	public void setMaxContentSize(Short maxContentSize) {
		this.maxContentSize = maxContentSize;
	}

	
	
	
	public String getAllowOrgId() {
		return allowOrgId;
	}
	public void setAllowOrgId(String allowOrgId) {
		this.allowOrgId = allowOrgId;
	}
	public Boolean getIsAllowEdit() {
		return isAllowEdit;
	}
	public void setIsAllowEdit(Boolean isAllowEdit) {
		this.isAllowEdit = isAllowEdit;
	}
	public String getAllowUserId() {
		return allowUserId;
	}
	public void setAllowUserId(String allowUserId) {
		this.allowUserId = allowUserId;
	}
	public int hashCode() {
	
		return super.hashCode();
	}

	public boolean equals(Object obj) {
		if(! (obj instanceof ColumnMuDTO)){
			return false;
		}
		
		if(this.id == null || ((ColumnMuDTO)obj).getId() ==null){
			return false;
		}
		
		return this.id.equals(((ColumnMuDTO)obj).getId());
	}
	
	

	
}
