package com.wb.web.system.dto.log;

import java.util.Date;

import com.wb.core.common.dto.UUIDDto;

public class LoginCountDTO extends UUIDDto {

	private int sort;// 序号
	private String username; // 用户名
	private Date loginDate; // 最近一次登录时间
	private int num; // 登录次数

	public LoginCountDTO() {
	}

	public LoginCountDTO(int sort, String username, Date loginDate, int num) {
		super();
		this.sort = sort;
		this.username = username;
		this.loginDate = loginDate;
		this.num = num;
	}

	@Override
	public String toString() {
		return "LoginCountDTO [sort=" + sort + ", username=" + username
				+ ", loginDate=" + loginDate + ", num=" + num + ", getId()="
				+ getId() + ", getLastOperatorTime()=" + getLastOperatorTime()
				+ "]";
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
