package com.zens.unify.task;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.task.handler.QuartzManager;

/**
 * 初始化运行
 * @author vector
 * @time 2014年7月20日 下午8:42:42
 *
 */
public class Init {

	@Autowired
	TaskConfigService configService;
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	public void init() throws SchedulerException{
		QuartzManager quartz = new QuartzManager(schedulerFactoryBean);
		quartz.init(configService.find(new TaskConfig()));
	}
	
}
