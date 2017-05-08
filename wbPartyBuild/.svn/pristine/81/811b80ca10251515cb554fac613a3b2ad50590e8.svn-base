package com.wb.web.study.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;
import com.wb.web.system.entity.User;

/**
 * 学习任务
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_study_task")
public class StudyTask extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2031838896041669072L;
	
	public static final int VAILD_STATUS = 1;
	public static final int INVAILD_STATUS = 0;
	
	private String taskNum;	   //任务编号
	private String taskName;   //任务名称
	private String taskMemo;   //任务说明
	private Date startTime;    //开始时间
	private Date endTime;	   //结束时间
	private Integer status;    //任务状态  0：无效  1：有效
	private Set<StudyData> studyData = new HashSet<StudyData>();
	private ExamPaper examPaper;    //测试试卷id
	
	private Set<User> finishUers = new HashSet<User>(); //完成任务的用户
	
	private Date createTime;   //创建时间
	private String createBy;   //创建者	
	private String updateBy;   //修改者
	
	public StudyTask(){}
	
	public StudyTask(Long taskStu) {
		setId(taskStu);
	} 
	
	
	public StudyTask(String taskNum, String taskName, String taskMemo,
			Date startTime, Date endTime, Integer status, Set<StudyData> studyData,
			ExamPaper examPaper,String createBy, String updateBy) {
		super();
		this.taskNum = taskNum;
		this.taskName = taskName;
		this.taskMemo = taskMemo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.studyData = studyData;
		this.examPaper = examPaper;
		this.createTime = new Date();
		this.createBy = createBy;
		this.updateBy = updateBy;
	}
	
	
	
	@Column(name="task_num",nullable=false,length=20)
	public String getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	
	@Column(name="task_name",nullable=false,length=50)
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	@Column(name="task_memo",length=500)
	public String getTaskMemo() {
		return taskMemo;
	}
	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}
	
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="status",nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	@ManyToMany(targetEntity=StudyData.class)  
    @JoinTable(name = "c_study_task_data", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = { @JoinColumn(name = "data_id") })  	
	public Set<StudyData> getStudyData() {
		return studyData;
	}
	public void setStudyData(Set<StudyData> studyData) {
		this.studyData = studyData;
	}
	
	
	@ManyToOne(targetEntity=ExamPaper.class)
	@JoinColumn(name="exam_paper_id",unique=false)
	public ExamPaper getExamPaper() {
		return examPaper;
	}
	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}
	
	
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="create_by",length=50)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Column(name="update_by",length=50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="c_task_finish_user",joinColumns={@JoinColumn(name="task_id")},
	   inverseJoinColumns={@JoinColumn(name="user_id")})
	public Set<User> getFinishUers() {
		return finishUers;
	}
	public void setFinishUers(Set<User> finishUers) {
		this.finishUers = finishUers;
	}
	
	
	
	
	
	
	

}
