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
 * 学习资料
 * 
 * @author wb_java_zjr
 * 
 */
@Entity
@Table(name = "c_study_data")
public class StudyData extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112548475497389890L;

	private String dataNum; // 资料编号
	private String dataName; // 资料名称
	private String dataMemo; // 资料备注
	private StudyCategory studyCategory; // 类别
	private Long baseFileId; // 文件id（com.wb.web.base.entity.BaseFile类的id）
	private Date createTime; // 创建时间
	private String createBy; // 创建者
	private String updateBy; // 修改者
	private Set<StudyTask> studyTask = new HashSet<StudyTask>();

	public StudyData() {
	};

	public StudyData(Long id) {
		setId(id);
	};

	/**
	 * 资料管理的构造方法
	 * 
	 * @param dataName
	 * @param dataMemo
	 * @param studyCategory
	 * @param baseFileId
	 */
	public StudyData(String dataNum, String dataName, String dataMemo,
			StudyCategory studyCategory, Long baseFileId, String createBy,
			String updateBy) {
		super();
		this.dataNum = dataNum;
		this.dataName = dataName;
		this.dataMemo = dataMemo;
		this.studyCategory = studyCategory;
		this.baseFileId = baseFileId;
		this.createTime = new Date();
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return "StudyData [dataNum=" + dataNum + ", dataName=" + dataName
				+ ", dataMemo=" + dataMemo + ", studyCategory=" + studyCategory
				+ ", baseFileId=" + baseFileId + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateBy=" + updateBy
				+ ", studyTask=" + studyTask + ", getId()=" + getId()
				+ ", getLastOperatorTime()=" + getLastOperatorTime() + "]";
	}

	@Column(name = "data_num", nullable = false, length = 20)
	public String getDataNum() {
		return dataNum;
	}

	public void setDataNum(String dataNum) {
		this.dataNum = dataNum;
	}

	@Column(name = "data_name", nullable = false, length = 80)
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	@Column(name = "data_memo", length = 200)
	public String getDataMemo() {
		return dataMemo;
	}

	public void setDataMemo(String dataMemo) {
		this.dataMemo = dataMemo;
	}

	@ManyToOne(targetEntity = StudyCategory.class)
	@JoinColumn(name = "study_category_id")
	public StudyCategory getStudyCategory() {
		return studyCategory;
	}

	public void setStudyCategory(StudyCategory studyCategory) {
		this.studyCategory = studyCategory;
	}

	@Column(name = "base_file_id")
	public Long getBaseFileId() {
		return baseFileId;
	}

	public void setBaseFileId(Long baseFileId) {
		this.baseFileId = baseFileId;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_by", length = 50)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "update_by", length = 50)
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@ManyToMany(targetEntity = StudyTask.class)
	@JoinTable(name = "c_study_task_data", joinColumns = { @JoinColumn(name = "data_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
	public Set<StudyTask> getStudyTask() {
		return studyTask;
	}

	public void setStudyTask(Set<StudyTask> studyTask) {
		this.studyTask = studyTask;
	}

}
