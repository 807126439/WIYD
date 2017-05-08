package com.wb.core.timer;




import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.wb.web.system.dao.ITimeTaskDao;
import com.wb.web.system.entity.TimeTask;

/**
 * 读取数据库 然后判断是否启动任务
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 */
public class DataBaseSchedulerFactoryBean extends SchedulerFactoryBean {
	
	
	private ITimeTaskDao timeTaskDao;
	
	
	
	/**
	 * 读取数据库判断是否开始定时任务
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		String[] trigerrNames = this.getScheduler().getTriggerNames(Scheduler.DEFAULT_GROUP);
		TimeTask task;
		
		for (String trigerrName : trigerrNames) {
			task = timeTaskDao.getUniqueByProperty("taskId", trigerrName);
		
			//数据库查询不到的定时任务或者定时任务的运行状态不为1时，都停止
			//TASK #327 定时器任务默认未启动 
			if(task==null || !"1".equals(task.getIsStart())){
				this.getScheduler().pauseTrigger(trigerrName,Scheduler.DEFAULT_GROUP);
			}
		}
	}
	public void setTimeTaskDao(ITimeTaskDao timeTaskDao) {
		this.timeTaskDao = timeTaskDao;
	}

	
	
	
}
