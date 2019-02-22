package com.zens.unify.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zens.unify.entity.Constants;
import com.zens.unify.service.DataService;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.task.handler.TimeTask;

/**
 * todo：天气定时任务
 * @author vector
 * @date 2014年7月15日 下午2:40:38
 */
public class ShgoldJob {
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	TaskConfigService taskConfigService;
	
	@Autowired
	DataService dataService;
	/**
	 * 定时抓取天气信息
	 * @return
	 */
	public void grab(){

		TimeTask task = new TimeTask(Constants.Data.shgold.name(), taskConfigService, dataService);
		task.grab();
		log.info("黄金数据抓取成功");
	}
}
