package com.zens.unify;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.zens.unify.entity.CameraBase;
import com.zens.unify.entity.WeatherUni;

public class T {

	
	public static void main(String[] args) {
		String  s = "<em class=\"smallRedball\">01</em> <em class=\"smallRedball\">07</em> <em class=\"smallRedball\">09</em> <em class=\"smallRedball\">16</em> <em class=\"smallRedball\">20</em> <em class=\"smallRedball\">23</em> <em class=\"smallBlueball\">06</em> <a href=\"/award/ssq/2015001.html#anchorLink\">计算奖金</a><p class=\"ssqExtraNum\"><span class=\"jtip\" inf=\"幸运蓝色球号码只对当期二等奖（需中6个红球）派奖有效，其余各奖级中奖条件和奖金规定不变。\">幸运蓝球：<strong>01</strong><i class=\"questionMark\"></i></span></p>";
		System.out.println(s.replaceAll("<a[^>]+>[^<]*</a>", ""));
//		String json = "{\"dirPrefix\":\"CamRec_\",\"dirSuffix\":\"-554\",\"filePrefix\":\"CamRec-\",\"fileSuffix\":\".ts\",\"ssh\":{\"host\":\"192.168.0.1\",\"user\":\"root\",\"passwd\":\"rabbit\",\"port\":\"22\"}}";
//		
//		Gson gson = new Gson();
//		CameraBase base = gson.fromJson(json, CameraBase.class);
//		System.out.println(base);
		
		/*String str = "{\"resultcode\":\"200\",\"reason\":\"successed\",\"result\":{\"sk\":{\"temp\":\"30\",\"wind_direction\":\"西北风\",\"wind_strength\":\"1级\",";
		str += "\"humidity\":\"71%\",\"time\":\"17:15\"},\"today\":{\"city\":\"松江\",\"date_y\":\"2014年07月16日\",\"week\":\"星期三\",\"temperature\":\"26℃~32℃\",\"weather\":\"阵雨转阴\",";
		str += "\"weather_id\":{\"fa\":\"03\",\"fb\":\"02\"},\"wind\":\"南风小于3级\",\"dressing_index\":\"热\",\"dressing_advice\":\"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。\",\"uv_index\":\"弱\",";
		str += "\"comfort_index\":\"较不舒适\",\"wash_index\":\"不宜\",\"travel_index\":\"适宜\",\"exercise_index\":\"较不宜\",\"drying_index\":\"干燥\"},\"future\":{\"day_20140716\":{\"temperature\":\"26℃~32℃\",";
		str += "\"weather\":\"阵雨转阴\",\"weather_id\":{\"fa\":\"03\",\"fb\":\"02\"},\"wind\":\"南风小于3级\",\"week\":\"星期三\",\"date\":\"20140716\"},\"day_20140717\":{\"temperature\":\"26℃~34℃\",\"weather\":\"多云\",";
		str += "\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"东南风4-5级\",\"week\":\"星期四\",\"date\":\"20140717\"},\"day_20140718\":{\"temperature\":\"25℃~33℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},";
		str += "\"wind\":\"东南风4-5级\",\"week\":\"星期五\",\"date\":\"20140718\"},\"day_20140719\":{\"temperature\":\"26℃~34℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"南风3-4级\",\"week\":\"星期六\",\"date\":\"20140719\"},\"day_20140720\":{\"temperature\":\"27℃~35℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"南风转东南风3-4级\",\"week\":\"星期日\",\"date\":\"20140720\"},\"day_20140721\":{\"temperature\":\"27℃~34℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"东南风3-4级\",\"week\":\"星期一\",\"date\":\"20140721\"},\"day_20140722\":{\"temperature\":\"27℃~34℃\",\"weather\":\"多云\",\"weather_id\":{\"fa\":\"01\",\"fb\":\"01\"},\"wind\":\"东南风3-4级\",\"week\":\"星期二\",\"date\":\"20140722\"}}},\"error_code\":0}";
		String regex = "\"fa\":\"\\d\\d\"";
		Pattern p = Pattern.compile(regex);  
	    Matcher m = p.matcher(str);
	    
	    Map<String, String> map = WeatherUni.toMap();
	    
	    while (m.find()){
	    	String fas = m.group();
	    	String fa = fas.split(":")[1].replace("\"", "");
	    	str = str.replaceAll(regex, "\""+map.get(fa)+"\"");
	    }
	    
	    regex = "\"fb\":\"\\d\\d\"";
		p = Pattern.compile(regex);  
	    m = p.matcher(str);
	    
	    while (m.find()){
	    	String fas = m.group();
	    	String fa = fas.split(":")[1].replace("\"", "");
	    	str = str.replaceAll(regex, "\""+map.get(fa)+"\"");
	    }
	    System.out.println(str);*/
	}
}
