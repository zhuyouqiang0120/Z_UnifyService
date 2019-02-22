package com.zens.unify.task.shanghai;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.zens.unify.entity.Data;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.DataService;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.utils.shanghai.SHHtmlParse;

/**
 * todo：定时任务
 * @author vector
 * @date 2014年7月15日 下午2:40:38
 */
public class SMMailJob{
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	TaskConfigService taskConfigService;
	
	@Autowired
	DataService dataService;
	
	/**
	 * 定时抓取信息
	 * @return
	 */
	public void grab(){
		//任务配置
		TaskConfig tc = taskConfigService.get(new TaskConfig("smMail"));
		Data data = new Data();
		if(tc != null && StringUtils.hasLength(tc.getUrl())){
			SHHtmlParse parse = new SHHtmlParse();
			String content = parse.smMail(tc.getUrl());
			String ip = null;
			try {
				InetAddress addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				log.info("ip地址获取错误:"+e.getMessage());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//原始Data
			Data oldData = dataService.getDataByTaskConfigId(tc.getId());
			data.setTaskConfigId(tc.getId());
			data.setUpdateTime(sdf.format(new Date()));
			data.setUpdateIp(ip);
			data.setData(content);
			if(oldData != null){
				data.setId(oldData.getId());
			 	data.setBackupData(oldData.getData());
			}
			if(data.getId() == null){
				dataService.save(data);
			}else{
				dataService.update(data);
			}
		}
		
		log.info("市民信箱抓取完成！");
	}

}
