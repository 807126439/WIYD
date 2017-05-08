package com.wb.web.study.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wb.core.common.entity.BaseEntity;


/**
 * 题目
 * @author wb_java_zjr
 *
 */
@Entity
@Table(name="c_topic")
public class Topic extends BaseEntity implements Serializable{

	

	private static final long serialVersionUID = -3617848144738570004L;
	private Integer topicType;  	//题目类型  1：单选  2：多选  3：判断  4：填空
	private Date drawTime;			//出题时间 
	private String questionText;	//问题文本
	private Integer score;          //题目分数
	private String optionA; 
	private String optionB; 
	private String optionC; 
	private String optionD; 
	private String optionE; 
	private String optionF; 
	private String optionG;
	
	private String explainA; 
	private String explainB; 
	private String explainC; 
	private String explainD; 
	private String explainE; 
	private String explainF;
	private String explainG;
		
	private String answer;			//答案
	private StudyCategory studyCategory;	//题目类别
	private String createBy;   		//创建者	
	private String updateBy;  	 	//修改者
	
	
	public Topic() {
		super();
	}
	public Topic(Long id) {
		setId(id);
	}

	public Topic(Date drawTime,StudyCategory studyCategory, String createBy, String updateBy){
		super();		
		this.drawTime = drawTime;
		this.studyCategory = studyCategory;
		this.createBy = createBy;
		this.updateBy = updateBy;		
	}
	
		

	@Column(name="topic_type",nullable=false)
	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	@Column(name="draw_time",nullable=false)
	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}
	
	
	
	@Column(name="question_text",nullable=false,length=350)
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Column(name="option_a",length=100)
	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	@Column(name="option_b",length=100)
	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	@Column(name="option_c",length=100)
	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	@Column(name="option_d",length=100)
	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	@Column(name="option_e",length=100)
	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	@Column(name="option_f",length=100)
	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	@Column(name="option_g",length=100)
	public String getOptionG() {
		return optionG;
	}

	public void setOptionG(String optionG) {
		this.optionG = optionG;
	}

	@Column(name="explain_a",length=300)
	public String getExplainA() {
		return explainA;
	}

	public void setExplainA(String explainA) {
		this.explainA = explainA;
	}

	@Column(name="explain_b",length=300)
	public String getExplainB() {
		return explainB;
	}

	public void setExplainB(String explainB) {
		this.explainB = explainB;
	}

	@Column(name="explain_c",length=300)
	public String getExplainC() {
		return explainC;
	}

	public void setExplainC(String explainC) {
		this.explainC = explainC;
	}

	@Column(name="explain_d",length=300)
	public String getExplainD() {
		return explainD;
	}

	public void setExplainD(String explainD) {
		this.explainD = explainD;
	}

	@Column(name="explain_e",length=300)
	public String getExplainE() {
		return explainE;
	}

	public void setExplainE(String explainE) {
		this.explainE = explainE;
	}

	@Column(name="explain_f",length=300)
	public String getExplainF() {
		return explainF;
	}

	public void setExplainF(String explainF) {
		this.explainF = explainF;
	}

	@Column(name="explain_g",length=300)
	public String getExplainG() {
		return explainG;
	}

	public void setExplainG(String explainG) {
		this.explainG = explainG;
	}

	@Column(name="answer",length=100)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


	@ManyToOne(targetEntity=StudyCategory.class)
	@JoinColumn(name="study_category_id")
	public StudyCategory getStudyCategory() {
		return studyCategory;
	}

	public void setStudyCategory(StudyCategory studyCategory) {
		this.studyCategory = studyCategory;
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
	
	@Column(name="score",length=5)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	


}	

	