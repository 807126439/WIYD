package com.wb.core.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public abstract class UUIDEntity {
	private String id;
	private Date lastOperatorTime = new Date();

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="last_operator_time")
	public Date getLastOperatorTime() {
		return lastOperatorTime;
	}

	public void setLastOperatorTime(Date lastOperatorTime) {
		this.lastOperatorTime = lastOperatorTime;
	}
	
	
}
