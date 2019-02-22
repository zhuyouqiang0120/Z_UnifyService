package com.zens.unify.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.axis.message.MessageElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zens.unify.entity.PageUtils;
import com.zens.unify.utils.XmltoJson;

import cn.com.WebXml.DomesticAirlineLocator;
import cn.com.WebXml.DomesticAirlineSoapStub;
import cn.com.WebXml.GetDomesticAirlinesTimeResponseGetDomesticAirlinesTimeResult;
/**
 * 航班查询
 * @author vector
 * @time 2014年8月4日 下午4:31:09
 *
 */
@Controller
public class AirLineQueryController {
	
	//webSerbice地址
	private String url = "http://webservice.webxml.com.cn/webservices/DomesticAirline.asmx?wsdl";
	/**
	 * 根据起始地址查询航班信息
	 * @param model
	 * @param from  开始地址
	 * @param to   结束地址
	 * @param date  日期
	 * @return 
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	@RequestMapping("/getAirLineByStation")
	public String getDomesticAirlinesTime(Model model,String from,String to,String date) throws MalformedURLException, RemoteException{
		System.out.println(from+"_----------" + to + "---------" + date);
		URL endpointURL = new URL(url);
		DomesticAirlineSoapStub service = new DomesticAirlineSoapStub(endpointURL, new DomesticAirlineLocator());
		GetDomesticAirlinesTimeResponseGetDomesticAirlinesTimeResult dateset = service.getDomesticAirlinesTime(from, to, date, "");
		MessageElement[] fOCElement = dateset.get_any();// 取得xml信息
		@SuppressWarnings("rawtypes")
		List fOCElementBody = fOCElement[1].getChildren();// 消息体信息,DataSet对象

		String content  = fOCElementBody.isEmpty() ? "" : fOCElementBody.get(0).toString();// 消息体的字符串形式

		String json = XmltoJson.xml2JSON(content);

		model.addAttribute("result", json);
			
		return PageUtils.JSON;
	}
}