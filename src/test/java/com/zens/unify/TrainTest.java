package com.zens.unify;

import java.net.URL;
import java.util.List;

import org.apache.axis.message.MessageElement;
import org.jsoup.nodes.FormElement;

import com.zens.unify.utils.XmltoJson;

import cn.com.WebXml.GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult;
import cn.com.WebXml.TrainTimeWebServiceLocator;
import cn.com.WebXml.TrainTimeWebServiceSoapStub;

public class TrainTest {
	public static void main(String[] args) {
		try{
			
//			String startStation = new String(startStation.getBytes("iso-8859-1"), "utf-8");
//			String arriveStation = new String(arriveStation.getBytes("iso-8859-1"), "utf-8");贵阳
			String startStation = "上海";
			String arriveStation = "贵阳";
			String url = "http://webservice.webxml.com.cn/WebServices/TrainTimeWebService.asmx?wsdl";
			{

				URL endpointURL = new URL(url);

				TrainTimeWebServiceSoapStub service = new TrainTimeWebServiceSoapStub(endpointURL, new TrainTimeWebServiceLocator());
				
				//根据列车编号查询（如：D12）
//				service.getDetailInfoByTrainCode("", null);
				
				GetStationAndTimeByStationNameResponseGetStationAndTimeByStationNameResult dateset = service.getStationAndTimeByStationName(startStation,arriveStation, "");
				
				MessageElement[] FOCElement = dateset.get_any();// 取得xml信息

//				List<FormElement> FOCElementHead = FOCElement[0].getChildren();// 消息头,DataSet对象

				List FOCElementBody = FOCElement[1].getChildren();// 消息体信息,DataSet对象

				String content  = FOCElementBody.isEmpty() ? "" : FOCElementBody.get(0).toString();// 消息体的字符串形式

				System.out.println(XmltoJson.xml2JSON(content));
			} 
			}catch(Exception e){
				e.printStackTrace();
			}
	}
}
