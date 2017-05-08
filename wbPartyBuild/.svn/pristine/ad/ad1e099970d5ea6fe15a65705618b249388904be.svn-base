package com.wb.core.timer;



import org.springframework.scheduling.quartz.CronTriggerBean;

import com.wb.web.system.dao.ITimeTaskDao;
import com.wb.web.system.entity.TimeTask;

/**
 * 在原有功能的基础上面增加数据库的读取
 * @author wb_java_zjr
 * 
 */
public class DataBaseCronTriggerBean extends CronTriggerBean{

	private static final long serialVersionUID = 1L;
	
	
	private ITimeTaskDao timeTaskDao;
	/**
	 * 读取数据库更新文件
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		TimeTask task = this.timeTaskDao.getUniqueByProperty("taskId", this.getName());

		if(task!=null && task.getIsEffect().equals("1")
				&& !task.getCronExpression().equals(this.getCronExpression())){
			this.setCronExpression(task.getCronExpression());
			DynamicTask.updateSpringMvcTaskXML(this,task.getCronExpression());
		}
	}
	
	public void setTimeTaskDao(ITimeTaskDao timeTaskDao) {
		this.timeTaskDao = timeTaskDao;
	}

	
	
}
