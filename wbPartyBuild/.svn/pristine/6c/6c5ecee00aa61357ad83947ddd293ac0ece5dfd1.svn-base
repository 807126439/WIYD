package com.wb.core.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.wb.core.utils.ColorCSS;


public class FormPage<T> {

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private List<T> list = new ArrayList<T>();
	private List<FormItem> items = new ArrayList<FormItem>();
	private Long recTotal = 0L;
	private Long loadedNum;
	
	
	public FormPage() {
		super();
	}

	public FormPage(Integer currentPage, Integer pageSize, List<T> list,Long recTotal) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.list = list;
		this.recTotal = recTotal;
	}

	/**
	 * 生成页码列表
	 */
	public void makePageItem() {
		
		Integer range = 8; // 最大范围数
		Integer totalPage = getTotalPage().intValue(); // 总页数

		Integer first = null;
		Integer last = null;

		if (currentPage <= (range / 2) || totalPage<=range) {
			first = 1;
			last = totalPage > range ? range : totalPage;

		} else {
			first = currentPage - (range / 2) + 1;

			if (totalPage > (currentPage + (range / 2))) {

				last = currentPage + (range / 2);

			} else {
				last = totalPage;
				int n = (int) (range / 2 - (last - currentPage));
				for (int i = 0; i < n; i++) {
					--first;
				}
			}

		}
	    
		FormItem begin = new FormItem("首页", currentPage <= 1? 0 : 1, currentPage <= 1? "pn-prev disabled":"pn-prev");
		FormItem pre = new FormItem("上一页", currentPage <= 1? 0 :(currentPage-1), currentPage <= 1? "pn-prev disabled":"pn-prev","&lt;");
	
		items.add(begin);
		items.add(pre);

		Integer num = (int) (last - first);
		for (int i = 0; i <= num; i++) {
			FormItem item = new FormItem();
			if (first == currentPage) {
				item.setOn(ColorCSS.CURRENT);
			}
			item.setName(String.valueOf(first));
			item.setValue(first);
		
			items.add(item);
			first++;
		}
		FormItem next = new FormItem("下一页", currentPage >= totalPage? 0 : currentPage+1, currentPage >= totalPage ? "pn-next disabled":"pn-next");
		FormItem end = new FormItem("末页", currentPage >= totalPage? 0 :totalPage, currentPage >= totalPage ? "pn-next disabled":"pn-next","&gt;");
	

		items.add(next);
		items.add(end);
	}
	
	
	
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getRecTotal() {
		return recTotal;
	}

	public void setRecTotal(Long recTotal) {
		this.recTotal = recTotal;
	}

	public Long getTotalPage(){
		if(this.recTotal % this.pageSize == 0){
			return this.recTotal / this.pageSize; 
		}else{
			return this.recTotal / this.pageSize + 1;
		}
	}
	
    public Long getLoadedNum(){
		
		return (long) ((this.currentPage-1)*this.pageSize+this.list.size());
	}

	public List<FormItem> getItems() {
		return items;
	}

	public void setItems(List<FormItem> items) {
		this.items = items;
	}

    
  
}
