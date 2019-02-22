/**
 * 
 */
package com.zens.unify.task.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.zens.unify.entity.TaskConfig;
import com.zens.unify.utils.StringUtils;

/**
 * 定时器管理
 * @author vector
 * @time 2014年7月19日 下午2:52:41
 *
 */
public class QuartzManager {
	
	private Scheduler scheduler;
	
	
	public QuartzManager(SchedulerFactoryBean schedulerFactoryBean) {
		super();
		scheduler = schedulerFactoryBean.getScheduler();
	}
	/**
	 * 初始化
	 * @throws SchedulerException
	 */
	public void init(List<TaskConfig> tasks) throws SchedulerException{
		Map<String, String> crons = new HashMap<String, String>();
		for(TaskConfig task : tasks){
			if(task.getCode() != null && task.getCycle() != null)
				crons.put(task.getCode()+"JobCronTrigger", task.getCycle());
		}
		//定时器中的所有key
		Set<TriggerKey> keys = scheduler.getTriggerKeys(null);
		for(TriggerKey key : keys){
			String cycle = crons.get(key.getName());
			if(StringUtils.hasText(cycle)){
				rescheduleJob(key,cycle);
			}
			if("weatherJobCronTrigger".equals(key.getName()))
				deleteJob(key.getName());
		}
	}
	
	/**
	 * 修改时间重新运行
	 * @param key
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public void rescheduleJob(String key, String cronExpression) throws SchedulerException{
		rescheduleJob(TriggerKey.triggerKey(key+"JobCronTrigger"),cronExpression);
	}
	/**
	 * 修改后重新运行
	 * @param triggerKey
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	private void rescheduleJob(TriggerKey key, String cronExpression) throws SchedulerException{
		 
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(key);
		 
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		 
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(key)
		    .withSchedule(scheduleBuilder).build();
		 
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(key, trigger);
	}
	
	/**
	 * 暂停运行
	 * @param key
	 * @throws SchedulerException
	 */
	public void pauseJob(String key) throws SchedulerException{
		if(scheduler.isStarted())
			scheduler.pauseJob(getJobKey(key));
	}
	
	/**
	 * 重新运行
	 * @param key
	 * @throws SchedulerException
	 */
	public void resumeJob(String key) throws SchedulerException{
		if(!scheduler.isStarted())
			scheduler.resumeJob(getJobKey(key));
	}
	
	/**
	 * 删除
	 * @param key
	 * @throws SchedulerException
	 */
	public void deleteJob(String key) throws SchedulerException{
		scheduler.deleteJob(getJobKey(key));
	}
	
	/**
	 * 立即执行
	 * @param key
	 * @throws SchedulerException
	 */
	public void triggerJob(String key) throws SchedulerException{
		/*Set<JobKey> keys = scheduler.getJobKeys(null);
		for (JobKey jobKey : keys) {
			System.out.println(jobKey.getName());
		}*/
	System.out.println(key);
		scheduler.triggerJob(getJobKey(key));
	}
	/**
	 * jobKey
	 * @param key
	 * @return
	 */
	private JobKey getJobKey(String key){
		return JobKey.jobKey(key+"JobDetail");
	}
}