package com.zens.unify.web.shanghai;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zens.unify.entity.PageUtils;
import com.zens.unify.utils.shanghai.SHHtmlParse;

/**
 * 便民查询
 * @author vector
 * @time 2014年8月18日 下午5:34:31
 *
 */
@Controller
public class Query {
	
	/**
	 * 违章查询
	 * @param cNo 车牌号
	 * @param eNo 发动机号
	 * @return
	 */
	@RequestMapping("/illegal/{cNo}/{eNo}")
	public String queryIllegal(Model model,@PathVariable("cNo")String cNo,@PathVariable("eNo")String eNo){
		SHHtmlParse parse = new SHHtmlParse();
		String result = parse.illega(cNo, eNo);
		model.addAttribute("result", result);
		return PageUtils.JSON;
	}
	/**
	 * 路况信息
	 * @param model
	 * @param road
	 * @return
	 */
	@RequestMapping("/traffic/{road}")
	public String traffic(Model model,@PathVariable("road")String road){
		SHHtmlParse parse = new SHHtmlParse();
		String result = parse.trafficInfoParse(road);
		model.addAttribute("result", result);
		return PageUtils.JSON;
	}
	/**
	 * 
	 * @return
	 */
	public String trafficIllegal(){
		//http://www.shjtaq.com/Server1/dzjc_new.asp?
		return PageUtils.JSON;
	}
}
