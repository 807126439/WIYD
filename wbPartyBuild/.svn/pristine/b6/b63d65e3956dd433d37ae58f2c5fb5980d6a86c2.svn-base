package com.wb.web.portals.dto.column;

import java.util.List;

import com.wb.web.portals.dto.content.InnerShowContentDTO;

/**
 * 整个内页展示的对象
 * @author wb_java_zjr
 *
 */
public class InnnerColumnDTO {

	private ColumnHead topColumn;		    //顶级栏目
	private List<ColumnHead> childColums;   //子栏目集合	
	private ColumnHead currColumn;			//当前栏目
	
	
	private List<ColumnHead> navColums;     //右边导航集合
	private List<InnerShowContentDTO> contentList; //文章内容集合
	private Integer ctCurPage;				//文章分页的当前页
	private Integer ctTotalPage;			//文章总页数
	
	private InnerShowContentDTO singalContent;
	
	
	

	/**
	 * 单页面构造方法
	 */
	public InnnerColumnDTO(List<ColumnHead> childColums,
			ColumnHead currColumn, List<ColumnHead> navColums,
			InnerShowContentDTO singalContent) {
		super();
		this.topColumn = navColums.isEmpty()?null:navColums.get(0);
		this.childColums = childColums;
		this.currColumn = currColumn;
		this.navColums = navColums;
		this.singalContent = singalContent;
	}


	/**
	 * 文章列表构造方法
	 * @param topColumn
	 * @param childColums
	 * @param currColumn
	 * @param navColums
	 * @param contentList
	 * @param ctCurPage
	 * @param ctTotalPage
	 */
	public InnnerColumnDTO(List<ColumnHead> childColums,
			ColumnHead currColumn, List<ColumnHead> navColums,
			List<InnerShowContentDTO> contentList, Integer ctCurPage,
			Integer ctTotalPage) {
		super();
		this.topColumn = navColums.isEmpty()?null:navColums.get(0);
		this.childColums = childColums;
		this.currColumn = currColumn;
		this.navColums = navColums;
		this.contentList = contentList;
		this.ctCurPage = ctCurPage;
		this.ctTotalPage = ctTotalPage;
	}
	
	
	public ColumnHead getTopColumn() {
		return topColumn;
	}
	public void setTopColumn(ColumnHead topColumn) {
		this.topColumn = topColumn;
	}
	public List<ColumnHead> getChildColums() {
		return childColums;
	}
	public void setChildColums(List<ColumnHead> childColums) {
		this.childColums = childColums;
	}
	


	public ColumnHead getCurrColumn() {
		return currColumn;
	}
	public void setCurrColumn(ColumnHead currColumn) {
		this.currColumn = currColumn;
	}
	public List<ColumnHead> getNavColums() {
		return navColums;
	}
	public void setNavColums(List<ColumnHead> navColums) {
		this.navColums = navColums;
	}
	public List<InnerShowContentDTO> getContentList() {
		return contentList;
	}
	public void setContentList(List<InnerShowContentDTO> contentList) {
		this.contentList = contentList;
	}

	public Integer getCtCurPage() {
		return ctCurPage;
	}
	public void setCtCurPage(Integer ctCurPage) {
		this.ctCurPage = ctCurPage;
	}

	public Integer getCtTotalPage() {
		return ctTotalPage;
	}

	public void setCtTotalPage(Integer ctTotalPage) {
		this.ctTotalPage = ctTotalPage;
	}


	public InnerShowContentDTO getSingalContent() {
		return singalContent;
	}


	public void setSingalContent(InnerShowContentDTO singalContent) {
		this.singalContent = singalContent;
	}
	
	
	
	
}
