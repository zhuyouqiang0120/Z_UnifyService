package com.zens.unify.task.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.zens.unify.entity.Data;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.DataService;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.task.halper.JsoupHelper;
import com.zens.unify.utils.HtmlParse;
import com.zens.unify.utils.StringUtils;
import com.zens.unify.utils.UrlWebContentUtils;

/**
 * todo：定时任务
 * @author vector
 * @date 2014年7月15日 下午2:40:38
 */
public class TimeTask {
	Logger log = Logger.getLogger(getClass());
	
	TaskConfigService taskConfigService;
	
	DataService dataService;
	
	private TaskConfig config;
	private String code;
	
	public TimeTask(String code,TaskConfigService taskConfigService,
			DataService dataService) {
		super();
		this.code = code;
		this.taskConfigService = taskConfigService;
		this.dataService = dataService;
	}

	/**
	 * 定时抓取信息
	 * @return
	 */
	public void grab(){
		init();
		Data data = new Data();
		
		String url = config.getUrl();
		
		String ip = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			log.info("ip地址获取错误:"+e.getMessage());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//原始Data
		Data oldData = dataService.getDataByTaskConfigId(config.getId());
		
		//彩票
		if("lottery".equals(this.code)){
			//抓取
			data.setData(new HtmlParse().lottery(url));
		}else if("air".equals(this.code)){
			data.setData(new JsoupHelper().air(url));
		}else if("weather".equals(this.code)){
			data.setData(new JsoupHelper().weather(url));
		}else if("oil_city".equals(this.code)){
			data.setData(new JsoupHelper().oil(url));
		}else if("shgold".equals(this.code)){
			data.setData(new JsoupHelper().gold(url));
		}else if("rmbquot".equals(this.code)){
			data.setData(new JsoupHelper().rmbquot(url));
		}else{
			//抓取
			data.setData(new UrlWebContentUtils().getOneHtml(url));
		}
		
		if(StringUtils.hasText(data.getData())){
			data.setTaskConfigId(config.getId());
			data.setUpdateTime(sdf.format(new Date()));
			data.setUpdateIp(ip);
			if(oldData != null){
				data.setId(oldData.getId());
			 	data.setBackupData(oldData.getData());
			}
			if(data.getId() == null){
				dataService.save(data);
			}else{
				dataService.update(data);
			}
			log.info("数据抓取成功！");
		}
	}
	
	/**
	 * 初始化获取抓取地址
	 */
	private void init(){
		config = taskConfigService.get(new TaskConfig(this.code));
	}
}
