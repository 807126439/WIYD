package com.wb.web.study.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;


/**
 * 成绩信息
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_exam_score")
public class ExamScore extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5478108061039302549L;
		
	private User user;			  //用户	
	private Date finishTime;  	  //完成时间
	private Long spendTime;    	  //所用时间（以秒为单位）
	private StudyTask studyTask;  //所属任务
	private Integer score;		  //分数
	

	
	public ExamScore() {
		super();
	}
	
	public ExamScore(User user, Date finishTime, Long spendTime,
			StudyTask studyTask, Integer score ) {
		super();
		this.user = user;
		this.finishTime = finishTime;
		this.spendTime = spendTime;
		this.studyTask = studyTask;
		this.score = score;
	}
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id",nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="finish_time")
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	@Column(name="spend_time")
	public Long getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(Long spendTime) {
		this.spendTime = spendTime;
	}
	
	
	
//	@ManyToOne(targetEntity=ExamPaper.class)
//	@JoinColumn(name="paper_id",nullable=false)
//	public ExamPaper getPaper() {
//		return paper;
//	}
//	
//	public void setPaper(ExamPaper paper) {
//		this.paper = paper;
//	}
	
	@ManyToOne(targetEntity=StudyTask.class)
	@JoinColumn(name="study_task_id",nullable=false)
	public StudyTask getStudyTask() {
		return studyTask;
	}
	public void setStudyTask(StudyTask studyTask) {
		this.studyTask = studyTask;
	}
	
	
	@Column(name="score",nullable=false)
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	

}
