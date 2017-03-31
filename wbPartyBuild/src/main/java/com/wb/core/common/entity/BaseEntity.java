package com.wb.core.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass  
public class BaseEntity {
	private Long id;
	private Date lastOperatorTime = new Date();
	
	@Id
	@Column(name="id",nullable=false)
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator", strategy = "identity")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
