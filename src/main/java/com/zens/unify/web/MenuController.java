package com.zens.unify.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zens.unify.entity.Menu;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.service.MenuService;

import java.sql.Date;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;

/**
 * 节目单
 * @author vector
 * @time 2014年9月11日 上午12:51:15
 *
 */
@Controller
public class MenuController {
	
	@Autowired MenuService service;
	
	@RequestMapping("/getMenu")
	public String menu(Model model,Menu m){
		Menu menu = service.get(m);
		String data = menu.getDate();
		data = data.replace("'", "\"");
		data = "{"+data+"}";
		model.addAttribute("result", menu == null ? "" : data);
		return PageUtils.JSON;
	}
	
	@RequestMapping("/getOldMenu")
	public String Oldmenu(Model model,Menu m){
		Menu menu = service.get(m);
		String data = menu.getDate();
		model.addAttribute("result", menu == null ? "" : data);
		return PageUtils.JSON;
	}
}
