package com.wb.web.portals.dto.column;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ColumnHead {

	private Integer indexNum;
	private Long colId;
	private String title;			//标题
	private String alias;			//英文名
	private String upperFirst; 		//alias第一位大写英文字母
	private Short typeId;			//栏目类型
	private Short showType; 
	private String linkUrl;
	
	private Boolean isHasChildren = false;
	private Boolean isOpen = false;
	private Long parId;
	private List<ColumnHead> childList = new ArrayList<ColumnHead>();

	public ColumnHead(){}
	
	public ColumnHead(Long colId){
		this.colId = colId;
		
	}
	
	public ColumnHead( Long colId, String title,Short typeId,String linkUrl) {
		super();
		this.colId = colId;
		this.title = title;
		this.typeId = typeId;
		this.linkUrl = linkUrl;
	}
	
	
	public ColumnHead(Long colId, String title,String alias,String linkUrl) {
		super();
		this.colId = colId;
		this.title = title;
		this.alias = alias;
		
		if(!StringUtils.isBlank(alias)){
			upperFirst = alias.substring(0, 1).toUpperCase();
		}
		
		this.linkUrl = linkUrl;
	}
	

	public ColumnHead(Integer indexNum, Long colId, String title) {
		super();
		this.indexNum = indexNum;
		this.colId = colId;
		this.title = title;
	}
	
	
	public Integer getIndexNum() {
		return indexNum;
	}


	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}


	public Long getColId() {
		return colId;
	}
	public void setColId(Long colId) {
		this.colId = colId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUpperFirst() {
		return upperFirst;
	}

	public void setUpperFirst(String upperFirst) {
		this.upperFirst = upperFirst;
	}

	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

		
	public Short getShowType() {
		return showType;
	}

	public void setShowType(Short showType) {
		this.showType = showType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	

	public Boolean getIsHasChildren() {
		return isHasChildren;
	}

	public void setIsHasChildren(Boolean isHasChildren) {
		this.isHasChildren = isHasChildren;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Long getParId() {
		return parId;
	}

	public void setParId(Long parId) {
		this.parId = parId;
	}

	public List<ColumnHead> getChildList() {
		return childList;
	}

	public void setChildList(List<ColumnHead> childList) {
		this.childList = childList;
	}

	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colId == null) ? 0 : colId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColumnHead other = (ColumnHead) obj;
		if (colId == null) {
			if (other.colId != null)
				return false;
		} else if (!colId.equals(other.colId))
			return false;
		return true;
	}


	


	
	
}
