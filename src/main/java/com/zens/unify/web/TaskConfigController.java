package com.zens.unify.web;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zens.unify.entity.Page;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.task.handler.QuartzManager;

/**
 * 功能管理
 * @author vector
 * @time 2014年7月16日 下午3:16:45
 *
 */
@Controller
@RequestMapping("/config")
public class TaskConfigController {

	@Autowired
	TaskConfigService configService;
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return PageUtils.TaskConfig.INDEX;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String list(Model model,Page<TaskConfig> page,TaskConfig config){
		page.setObject(config);
		configService.find(page);
		
		model.addAttribute("result", new Gson().toJson(page));
		
		return PageUtils.JSON;
	}
	
	@RequestMapping("getAll")
	public String getAll(Model model){
		List<TaskConfig> configs = configService.find(new TaskConfig());
		
		model.addAttribute("result", new Gson().toJson(configs));
		
		return PageUtils.JSON;
	}
	
	@RequestMapping("view/{id}")
	public String update(Model model,@PathVariable("id") Long id){

		TaskConfig config = configService.get(id);

		model.addAttribute("result", new Gson().toJson(config));
		return PageUtils.JSON;
	}
	
	@RequestMapping("update")
	public String update(Model model,TaskConfig config) throws SchedulerException{
		
		Integer i = null;
		if(config.getId() != null){
			TaskConfig tc = configService.get(config.getId());
			if(!tc.getCycle().equals(config.getCycle())){
				QuartzManager quartz = new QuartzManager(schedulerFactoryBean);
				quartz.rescheduleJob(config.getCode(), config.getCycle());
			}
			i = configService.update(config);
		}else
			i = configService.save(config);
		model.addAttribute("result", i);
		return PageUtils.TaskConfig.REDIRECT;
	}	

	@RequestMapping("pause")
	public String pause(Model model,String data) throws SchedulerException{
		List<TaskConfig> configs = new Gson().fromJson(data, new TypeToken<List<TaskConfig>>(){}.getType());

		QuartzManager quartz = new QuartzManager(schedulerFactoryBean);
		for(TaskConfig c : configs){
			if(c.getStatus() == 1){
				c.setStatus(0);
				quartz.pauseJob(c.getCode());
			}else{
				c.setStatus(1);
				quartz.resumeJob(c.getCode());
			}
			configService.update(c);
		}
		
		model.addAttribute("result", 1);
		return PageUtils.JSON;
	}	
	/**
	 * 理解
	 * @param model
	 * @param data
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping("run")
	public String run(Model model,String data) throws SchedulerException{
		List<TaskConfig> configs = new Gson().fromJson(data, new TypeToken<List<TaskConfig>>(){}.getType());

		QuartzManager quartz = new QuartzManager(schedulerFactoryBean);
		quartz.triggerJob("menu");
		//for(TaskConfig c : configs){
		//	quartz.triggerJob(c.getCode());
		//}
		
		model.addAttribute("result", 1);
		return PageUtils.JSON;
	}	
}