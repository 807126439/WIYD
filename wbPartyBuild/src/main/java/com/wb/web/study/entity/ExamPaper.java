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

/**
 * 试卷
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_exam_paper")
public class ExamPaper extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 401771726237772566L;
	
	private String paperName;    //试卷名称
	private Set<Topic> topics = new HashSet<Topic>();//题目
	private Integer examMinute; //考试时间（以分钟为单位）
	
	private Date createTime;   //创建时间
	private String createBy;   //创建者	
	private String updateBy;   //修改者
	
	private StudyCategory studyCategory; 

	private Integer paperType;  //1-随机，2-指定生成
	private Integer singleNum; //单选题数量
	private Integer multiNum;  //多选题数量
	private Integer blankNum;  //填空题数量
	private Integer judgeNum;  //判断题数量
		


	public ExamPaper() {}
	
	public ExamPaper(Long paperid) {
		setId(paperid);
	}
		
	public ExamPaper(String paperName, Integer examMinute,Integer paperType,
			Date createTime, String createBy, String updateBy) {
		super();
		this.paperName = paperName;
		this.examMinute = examMinute;
		this.paperType = paperType;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}
	

//	@ManyToOne(targetEntity=StudyTask.class)
//	@JoinColumn(name="study_task_id",unique=true,nullable=false)
//	public StudyTask getStudyTask() {
//		return studyTask;
//	}
//	public void setStudyTask(StudyTask studyTask) {
//		this.studyTask = studyTask;
//	}
		
	
	@Column(name="paper_name",nullable=false,length=100)
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	
	@Column(name="exam_minute",nullable=false)
	public Integer getExamMinute() {
		return examMinute;
	}
	public void setExamMinute(Integer examMinute) {
		this.examMinute = examMinute;
	}
	
	
	
	@ManyToMany(targetEntity=Topic.class)
	@JoinTable(name="c_paper_topic",joinColumns={@JoinColumn(name="paper_id")},
	inverseJoinColumns={@JoinColumn(name="topic_id")})
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
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
	
	
	@Column(name="paper_type",length=1)
	public Integer getPaperType() {
		return paperType;
	}

	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}

	@Column(name="single_num",length=5)
	public Integer getSingleNum() {
		return singleNum;
	}

	public void setSingleNum(Integer singleNum) {
		this.singleNum = singleNum;
	}

	@Column(name="multi_num",length=5)
	public Integer getMultiNum() {
		return multiNum;
	}

	public void setMultiNum(Integer multiNum) {
		this.multiNum = multiNum;
	}

	
	@Column(name="blank_num",length=5)
	public Integer getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
	}

	@Column(name="judge_num",length=5)
	public Integer getJudgeNum() {
		return judgeNum;
	}

	public void setJudgeNum(Integer judgeNum) {
		this.judgeNum = judgeNum;
	}

	
	@ManyToOne(targetEntity=StudyCategory.class)
	@JoinColumn(name="study_category_id")
	public StudyCategory getStudyCategory() {
		return studyCategory;
	}

	public void setStudyCategory(StudyCategory studyCategory) {
		this.studyCategory = studyCategory;
	}
	
	
	
	
	
	
}
