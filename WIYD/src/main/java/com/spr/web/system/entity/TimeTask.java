package com.spr.web.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.spr.core.common.entity.UUIDEntity;

/**
 * 定时任务
 * @author wb_java_zjr
 *
 */
public class TimeTask extends UUIDEntity implements Serializable{
 

    /**
	 * 
	 */
	private static final long serialVersionUID = -5088552556179449409L;

	private String taskId;

    private String taskDescribe;

    private String cronExpression;

    private String isEffect;

    private String isStart;

    private Date createDate;

    private String createBy;


   

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe == null ? null : taskDescribe.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart == null ? null : isStart.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

  
}