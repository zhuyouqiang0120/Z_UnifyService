package com.zens.unify.utils.shanghai;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.zens.unify.entity.SMMail;
import com.zens.unify.utils.HtmlParse;

/**
 * html文件解析
 * @author vector
 * @time 2014年7月23日 上午2:35:42
 *
 */
public class SHHtmlParse extends HtmlParse{
	Logger log = Logger.getLogger(getClass());
	
	/**
	 * 上海动态
	 * @param uri baseURL
	 * @return
	 */
	public String smMail(String uri){
		List<SMMail> dynamicList = new ArrayList<SMMail>();
		try {
			//获取页面返回dom对象
			Document doc = Jsoup.parse(new URL(uri+"Shdt.jsp"), 0);
			//根据条件获取对象
			Elements table = doc.getElementsByAttributeValue("cellpadding", "5");
			//得到table下面的tr
			Elements trs = table.get(0).getElementsByTag("tr");
			for(int i=1,len=trs.size()-3;i<len;i++){
				Element tr = trs.get(i);
				//获取标题和文章链接
				try{
					Element a = tr.child(1).child(0).child(0);//td -> div -> a
					String title = a.text();
					String href = uri + a.attr("href");
					String updateTime = tr.child(3).child(0).text();//td -> div
					String content = getSMMailContent(href);

					SMMail sm = new SMMail();
					sm.setContent(content);
					sm.setTitle(title);
					sm.setUpdateTime(updateTime);
					sm.setFrom("市民信箱");
					
					dynamicList.add(sm);
				}catch(Exception e){
					log.debug(e.getMessage());
				}
				
			}

		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		//上海动态
		map.put("dynamicData", dynamicList);
		//市民热点
		map.put("hotData", hot(uri));
		
		return new Gson().toJson(map);
	}
	/**
	 * 市民热点
	 * @param uri
	 * @return
	 */
	private List<SMMail> hot(String uri){

		List<SMMail> hotList = new ArrayList<SMMail>();
		try {
			//获取页面返回dom对象
			Document doc = Jsoup.parse(new URL(uri+"RedianList.jsp"), 0);
			//根据条件获取对象
			Elements as = doc.getElementsByAttributeValueStarting("href", "Redian.jsp?");
			for(Element a : as){
				String href = uri + a.attr("href");
				//二级栏目下
				doc = Jsoup.parse(new URL(href), 0);
				Elements ass =  doc.getElementsByAttributeValueStarting("href", "Redian.jsp?");
				for(Element article : ass){
					//文章
					href = article.attr("href");
					String sId = href.split("&")[1].split("=")[1];
					String url = uri + "RedianDetail.jsp?sId="+sId;
					String content = getSMMailContent(url);
					
					SMMail sm = new SMMail();
					sm.setContent(content);
					sm.setTitle(article.text());
					sm.setUpdateTime(null);
					sm.setFrom("市民信箱");
					
					hotList.add(sm);
				}
			}
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return hotList;
	}
	/**
	 * 获取文章内容
	 * @param uri
	 * @return
	 */
	private String getSMMailContent(String uri){
		String content = null;
		//获取页面返回dom对象
		try {
			Document doc = Jsoup.parse(new URL(uri), 0);
			//根据条件获取对象
			Elements table = doc.getElementsByAttributeValue("cellpadding", "5");
			Element div = table.get(0).getElementsByAttributeValue("align", "left").get(0);
			content = div.text();
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return content;
	}
	/**
	 * 市民信箱
	 * @param url
	 * @return
	 */
	/*public String smmail(String url){
		String baseUrl = "http://www.smmail.cn";
		String importentHref = "/smmail/ggxx/important/";//要闻导读
		String govInfoHref = "/smmail/ggxx/xxgk/";//政府信息
		String todayTipsHref = "/smmail/ggxx/hint/";//今天提示
		String entHref= "/smmail/ggxx/colligate/";//娱乐
		String focusHref = "/smmail/sy/guanzhu/";//热点关注
		String blogHref = "http://blog.smmail.cn/blog/smmail/jsp/main/MainPerson.jsp";//市民博客
		//重要新闻
		List<SMMail> importNewList = new ArrayList<SMMail>();
		//政府要闻
		List<SMMail> govInfoList = new ArrayList<SMMail>();
		//今日要闻
		List<SMMail> todayTipList = new ArrayList<SMMail>();
		//今日要闻
		List<SMMail> entList = new ArrayList<SMMail>();
		//热点关注
		List<SMMail> focusList = new ArrayList<SMMail>();
		//市民博客
		List<SMMail> blogList = new ArrayList<SMMail>();
		try {
			Document doc = Jsoup.parse(new URL(url), 20000);
			Elements importNews = doc.getElementsByAttributeValueStarting("href", importentHref);
			Elements govInfos = doc.getElementsByAttributeValueStarting("href", govInfoHref);
			Elements todayTips = doc.getElementsByAttributeValueStarting("href", todayTipsHref);
			Elements entNews = doc.getElementsByAttributeValueStarting("href", entHref);
			Elements hotFocus = doc.getElementsByAttributeValueStarting("href", focusHref);
			Elements blog = doc.getElementsByAttributeValueStarting("href", blogHref);
			for(Element ele : importNews){
				importNewList.add(getDynamicContent(baseUrl + ele.attr("href")));
			}
			for(Element ele : govInfos){
				govInfoList.add(getDynamicContent(baseUrl + ele.attr("href")));
			}
			for(Element ele : todayTips){
				todayTipList.add(getDynamicContent(baseUrl + ele.attr("href")));
			}
			for(Element ele : entNews){
				entList.add(getDynamicContent(baseUrl + ele.attr("href")));
			}
			for(Element ele : hotFocus){
				focusList.add(getDynamicContent(baseUrl + ele.attr("href")));
			}
			for(Element ele : blog){
				blogList.add(getDynamicContent(ele.attr("href"),"flag"));
			}
		} catch (MalformedURLException e) {
//			e.printStackTrace();
			log.debug(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.debug(e.getMessage());
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("importNewData", importNewList);
		map.put("govInfoData", govInfoList);
		map.put("todayTipData", todayTipList);
		map.put("entData", entList);
		map.put("hotFocusData", focusList);
		return new Gson().toJson(map);
	}
	*//**
	 * 内容页面
	 * @param subUri
	 * @return
	 *//*
	private SMMail getDynamicContent(String subUri,String...contentId){
		SMMail smMail = new SMMail();
		try {
			Document doc = Jsoup.parse(new URL(subUri), 20000);
			String title = null;
			String updateTimeStr = null;
			String from = null;
			String content = null;
			if(contentId.length == 0){
				Element contentDiv = doc.getElementById("contentdiv");
				title = contentDiv.child(0).text();
				String updateTime = contentDiv.child(1).text();
				if(StringUtils.hasText(updateTime)){
					String[] timeAndFrom = updateTime.split("来源：");
					updateTimeStr = timeAndFrom[0];
					from = timeAndFrom[1];
				}
				content = contentDiv.child(2).html().replaceAll("<a[^>]*(.*?)>(.*?)</a>", "").replace("相关链接：  ", "");
			}else{
				StringBuilder sb = new StringBuilder(250);
				sb.append("http://blog.smmail.cn/blogstore/user/");
				String userUUID = doc.getElementById("UserUUID_Hide").val();
				String articleUUID = doc.getElementById("ArticleUUID_Hide").val();
				if(StringUtils.hasText(userUUID))
					sb.append(userUUID.substring(0, 3)).append("/").append(userUUID).append("/article/").append(articleUUID);
				doc = Jsoup.parse(new URL(sb.toString()), 20000);
				Element article = doc.getElementsByTag("article").get(0);
				
				title = article.attr("title");
				updateTimeStr = article.attr("postDate");
				content = article.child(0).html().replace("&lt", "<").replace("&gt", ">");
			}
			smMail.setUpdateTime(updateTimeStr);
			smMail.setFrom(from);
			smMail.setTitle(title);
			smMail.setContent(content);
			
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		
		return smMail;
	}*/
	
	/**
	 * 车辆违章查询解析
	 * @return
	 */
	public String illega(String cNo,String eNo){
		String content = null;
		try {
			String url = "http://mail.sh.cn/NewMailSh/smmail/jsp/Portal/Clwz.jsp?CPH="+URLEncoder.encode(cNo,"gbk")+"&FDJH="+URLEncoder.encode(eNo,"gbk");
			Document doc = Jsoup.parse(new URL(url), 2000);
			Element table = doc.getElementsByAttributeValue("cellpadding", "5").get(0).child(0).child(0).child(0);
			content = table.html();
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
		}
		return content;
	}
	/**
	 * 路况信息
	 * @param road
	 * @return
	 */
	public String trafficInfoParse(String road){
		String content = null; 
		try {
			String url = "http://mail.sh.cn/NewMailSh/smmail/jsp/Portal/Lqxx.jsp?sKeyword="+URLEncoder.encode(road,"gbk");
			Document doc = Jsoup.parse(new URL(url), 0);
			Element table = doc.getElementsByAttributeValue("cellpadding", "5").get(0).child(0).child(0).child(0);
			content = table.html();
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return content;
	}
}