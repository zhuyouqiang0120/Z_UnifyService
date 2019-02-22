package com.zens.unify.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.WebXml.GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult;
import cn.com.WebXml.GetStationNameDataSetResponseGetStationNameDataSetResult;
import cn.com.WebXml.TrainTimeWebServiceLocator;
import cn.com.WebXml.TrainTimeWebServiceSoapStub;

import com.google.gson.Gson;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.utils.XmltoJson;
/**
 * 交通查询
 * @author vector
 * @time 2014年7月28日 下午5:22:26
 *
 */
@Controller
public class TrafficQueryController {
	Logger log = Logger.getLogger(getClass());

	private String url = "http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx?wsdl";
	
	/**
	 * 初始化方法
	 * @return
	 * @throws MalformedURLException
	 * @throws AxisFault
	 */
	private TrainTimeWebServiceSoapStub init() throws MalformedURLException, AxisFault{
		URL endpointURL = new URL(url);
		return new TrainTimeWebServiceSoapStub(endpointURL, new TrainTimeWebServiceLocator());
	}
	/**
	 * 根据其实站点查询
	 * @param from 起始站
	 * @param to 终点站
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStation/{from}/{to}")
	public String getStationAndTimeByStationName(@PathVariable("from")String from,@PathVariable("to")String to,Model model){
		String json = null;
		try {
			TrainTimeWebServiceSoapStub service = init();
			GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult dateset = service.getStationAndTimeByStationName(from,to, "");
			MessageElement[] fOCElement = dateset.get_any();// 取得xml信息
			@SuppressWarnings("rawtypes")
			List fOCElementBody = fOCElement[1].getChildren();// 消息体信息,DataSet对象

			String content  = fOCElementBody.isEmpty() ? "" : fOCElementBody.get(0).toString();// 消息体的字符串形式

			json = XmltoJson.xml2JSON(content);
		} catch (MalformedURLException e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		} catch (AxisFault e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		} catch (RemoteException e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		}

		model.addAttribute("result", json);
			
		return PageUtils.JSON;
	}
	
	/**
	 * 根据列车车次查询
	 * @param trainCode
	 * @param model
	 * @return
	 */
	@RequestMapping("getStation/{trainCode}")
	public String getStationAndTimeByTrainCode(@PathVariable("trainCode")String trainCode,Model model){
		String json = null;
		try {
			TrainTimeWebServiceSoapStub service = init();
			String[] content = service.getStationAndTimeByTrainCode(trainCode, "");
			Gson gson = new Gson();
			json = gson.toJson(content);
		} catch (AxisFault e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		} catch (MalformedURLException e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		} catch (RemoteException e) {
			log.info(e.getMessage());
//			e.printStackTrace();
		}

		model.addAttribute("result", json);
			
		return PageUtils.JSON;
	}
	
	@RequestMapping("/get")
	public String getStation(Model model){
		try {
			TrainTimeWebServiceSoapStub service = init();
			GetStationNameDataSetResponseGetStationNameDataSetResult dataSet = service.getStationNameDataSet();
			MessageElement[] mes = dataSet.get_any();
//			List fOCElementBody = mes[1].getChildren();
//			XmltoJson.xml2JSON(xml)
			for(MessageElement m : mes){
				System.out.println(m.getAsString());
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
