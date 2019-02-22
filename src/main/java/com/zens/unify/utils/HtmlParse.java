package com.zens.unify.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.zens.unify.entity.Lottery;

/**
 * html文件解析
 * @author vector
 * @time 2014年7月23日 上午2:35:42
 *
 */
public class HtmlParse {
	Logger log = Logger.getLogger(getClass());
	
	/**
	 * 彩票
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public String lottery(String url){
		
		Map<String, List<Lottery>> lotteries = new HashMap<String, List<Lottery>>();
		try {
			Document doc = Jsoup.parse(new URL(url), 20000);
			Elements awardList = doc.getElementsByClass("awardList");
			//数字彩票
			lotteries.put("digitalLottery", lottery(awardList.first(),7));
			//竞技体育
			lotteries.put("sportsLottery", lottery(awardList.get(1),3));
			
			//高频彩frequency of lottery
			lotteries.put("frequencyLottery", lottery(awardList.get(2),12));
			
		} catch (MalformedURLException e) {
			log.debug(e.getMessage());
//			e.printStackTrace();
		} catch (IOException e) {
			log.debug(e.getMessage());
//			e.printStackTrace();
		}
		
		return new Gson().toJson(lotteries);
	}
	/**
	 * 彩票种类
	 * @param table
	 * @return
	 */
	private List<Lottery> lottery(Element table,int line){
		
		List<Lottery> lotteries = new ArrayList<Lottery>();
		
		Map<String, Integer> lotteryKey = Lottery.getMapKeyByName();
		//tbody
		Element tbody = table.getElementsByTag("tbody").first();
		//tr
		Elements trs = tbody.children();
		for(int i=0,len=trs.size();i < (len < line ? len : line);i++){
			Element tr = trs.get(i);
			Lottery lottery = new Lottery();
			Elements tds = tr.children();
			if(tds.size() > 3){
				String lotteryName = tds.get(0).text();
				lottery.setName(lotteryName);
				lottery.setLotteryId(lotteryKey.get(lotteryName));
				lottery.setTimes(tds.get(1).text());
				lottery.setTime(tds.get(2).text());
				String s = tds.get(3).html();
				s = s.replaceAll("计算奖金", "").replaceAll("\"", "'").replaceAll("em", "span").replaceAll("<a[^>]+>[^<]*</a>", "");
				lottery.setNum(s);
				lotteries.add(lottery);
			}
		}
		return lotteries;
	}
	
}