package com.spr.core.common.entity;

import java.util.Date;

import com.spr.core.annotations.DbField;



public abstract class UUIDEntity {
	protected String id;
	protected Long sort;		//排序id
	protected Date gmtCreate;
	protected Date gmtModified;

	
	public UUIDEntity(){
		gmtCreate = new Date();
		gmtModified = gmtCreate;
	}
	
	
	@DbField(name="id")
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    @DbField(name="sort")
    public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	@DbField(name="gmt_create")
	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@DbField(name="gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UUIDEntity other = (UUIDEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public String toString() {
		 StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("id=").append(id);
	        sb.append(", gmtCreate=").append(gmtCreate);
	        sb.append(", gmtModified=").append(gmtModified);
	        sb.append("]");
	        return sb.toString();
		
	}
	
	
}
