package com.zens.unify.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.zens.unify.entity.Constants;
import com.zens.unify.entity.Data;
import com.zens.unify.entity.Page;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.DataService;
import com.zens.unify.service.TaskConfigService;

/**
 * 
 * @author vector
 * @time 2014年7月16日 下午9:40:50
 *
 */
@Controller
@RequestMapping("/data")
public class DataController {

	@Autowired
	DataService dataService;
	@Autowired
	TaskConfigService taskConfigService;
	
	@RequestMapping(value="index/{type}",method = RequestMethod.GET)
	public String index(Model model,@PathVariable("type") String type){
		model.addAttribute("type", type);
		return PageUtils.DATA_INDEX;
	}
	
	@RequestMapping("list/{type}")
	public String list(Model model,@PathVariable("type") String type,Page<Data> page){
		String result = "";
		//类型
		Constants.Data[] dataTypes = Constants.Data.values();
		for(Constants.Data dataType : dataTypes){
			if(dataType.name().equals(type)){
				TaskConfig config = new TaskConfig();
				config.setCode(type);
				config = taskConfigService.get(config);
				if(config != null){
					Data data = new Data(config.getId());
					page.setObject(data);
					
					page = dataService.find(page);
					result = new Gson().toJson(page);
				}
				break;
			}
		}
		model.addAttribute("result", result);
		return PageUtils.JSON;
	}
	/**
	 * 修改
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("update/{id}")
	public String update(Model model,@PathVariable("id") Long id){
		Data data = dataService.get(id);
		model.addAttribute("result", new Gson().toJson(data));
		return PageUtils.JSON;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Model model,Data data){
		Integer result = dataService.update(data);
		model.addAttribute("result", result);
		return PageUtils.JSON;
	}
	
	/**
	 * 请求接口
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping(value="{type}",method=RequestMethod.GET)
	public String data(Model model,@PathVariable("type") String type,String jsonp){
		
		String result = "";
		//类型
		Constants.Data[] dataTypes = Constants.Data.values();
		for(Constants.Data dataType : dataTypes){
			if(dataType.name().equals(type)){
				TaskConfig config = new TaskConfig();
				config.setCode(type);
				config = taskConfigService.get(config);
				if(config != null){
					Data data = dataService.getDataByTaskConfigId(config.getId());
					result = new Gson().toJson(data);
				}
				break;
			}
		}
		if(StringUtils.hasText(jsonp) && StringUtils.hasText(result)){
			result = jsonp + "("+result+")";
		}
		model.addAttribute("result", result);
		
		return PageUtils.JSON;
	}
}