package com.zens.unify.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.zens.unify.entity.Page;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.entity.Regions;
import com.zens.unify.service.RegionsService;
import com.zens.unify.utils.PinYinUtils;
import com.zens.unify.utils.StringUtils;

/**
 * 区域管理
 * @author vector
 * @time 2014年7月16日 下午3:16:45
 *
 */
@Controller
@RequestMapping("/regions")
public class RegionsController {

	@Autowired
	RegionsService regionService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return PageUtils.REGIONS;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String list(Model model,Page<Regions> page,Regions region){
		
		return getData(model, page, region);
	}
	
	/**
	 * 前台调用
	 * @param model
	 * @param page
	 * @param region
	 * @return
	 */
	@RequestMapping("data")
	public String data(Model model,Page<Regions> page,Regions region){
		page.setPageSize(-1);
		return getData(model, page, region);
	}
	
	private String getData(Model model,Page<Regions> page,Regions region){
		page.setObject(region);
		regionService.find(page);
		
		model.addAttribute("result", new Gson().toJson(page));
		
		return PageUtils.JSON;
	}
	
	@RequestMapping("update")
	public String update(Model model,Regions region){
		if(StringUtils.hasText(region.getName())){
			region.setCnName(PinYinUtils.getFirstSpell(region.getName()).toUpperCase());
			region.setUuid(StringUtils.uuid(PinYinUtils.getFullSpell(region.getName())));
		}
		int i;
		if(region.getId() != null)
			i = regionService.update(region);
		else
			i = regionService.save(region);
		model.addAttribute("result", i);
		return PageUtils.JSON;
	}
	@RequestMapping("delete/{id}")
	public String delete(Model model,@RequestParam("id") String id){
		String [] idArr = id.split(",");
		for(String idStr : idArr){
			Long ID = Long.valueOf(idStr);
			regionService.delete(ID);
		}
		model.addAttribute("result", 1);
		return PageUtils.JSON;
	}
}