package com.zens.unify.task.halper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zens.unify.entity.Air;
import com.zens.unify.entity.Gold;
import com.zens.unify.entity.Oil;
import com.zens.unify.entity.Rmbquot;
import com.zens.unify.entity.Rmbquot.RefePrice;
import com.zens.unify.entity.Weather;
import com.zens.unify.entity.Weather.Future;
import com.zens.unify.entity.Weather.Today;
import com.zens.unify.entity.Weather.Today.Weather_id;
import com.zens.unify.utils.UrlWebContentUtils;

/**
 * 天气抓去处理
 * 
 * @author vector
 * @date 2014年10月23日 下午12:21:04
 * @project Z_UnifyService
 * @package com.zens.unify.task.halper WeatherHelper.java
 *
 */
public class JsoupHelper {

	Logger log = Logger.getLogger(getClass());

	/**
	 * 
	 * @param url
	 * @return
	 */
	public String weather(String url) {
		Weather weather = new Weather();
		try {
			Today today = weather.new Today();
			Weather_id twid = today.new Weather_id();

			Document doc = Jsoup.parse(new URL(url), 2000);

			String cityName = doc.getElementsByClass("cityName").first()
					.child(0).text();// 城市名称
			today.setCity(cityName);

			Element s_d = doc.getElementById("7d");
			Element d1 = s_d.getElementsByAttributeValue("data-dn", "7d1").get(
					0);

			today.setDate_y(d1.child(0).text());
			today.setWeek(d1.child(1).text());
			twid.setFa(d1.child(2).attr("class").split(" ")[1].replace("d", ""));
			twid.setFb(d1.child(3).attr("class").split(" ")[1].replace("n", ""));
			today.setWeather_id(twid);
			today.setWeather(d1.child(4).text());
			Element tem1 = d1.child(5);
			Element tem2 = d1.child(6);
			today.setTemper1(tem1.child(0).text() + tem1.child(1).text());
			today.setTemper2(tem2.child(0).text() + tem2.child(1).text());

			Element win = d1.child(7);
			Element em = win.child(0);
			String w1 = em.child(0).attr("title");
			String w2 = em.child(1).attr("title");
			if (!w1.equals(w2)) {
				w1 += "转" + w2;
			}
			today.setFl(win.child(1).text());
			today.setWind(w1);

			List<Future> futures = new ArrayList<Weather.Future>();
			for (int i = 2; i < 8; i++) {
				Future future = weather.new Future();
				Future.Weather_id futureWeatherId = future.new Weather_id();
				d1 = s_d.getElementsByAttributeValue("data-dn", "7d" + i)
						.get(0);
				future.setDate(d1.child(0).text());
				future.setWeek(d1.child(1).text());
				futureWeatherId.setFa(d1.child(2).attr("class").split(" ")[1]
						.replace("d", ""));
				futureWeatherId.setFb(d1.child(3).attr("class").split(" ")[1]
						.replace("n", ""));
				future.setWeather_id(futureWeatherId);
				future.setWeather(d1.child(4).text());
				tem1 = d1.child(5);
				tem2 = d1.child(6);
				future.setTemper1(tem1.child(0).text() + tem1.child(1).text());
				future.setTemper2(tem2.child(0).text() + tem2.child(1).text());

				win = d1.child(7);
				em = win.child(0);
				w1 = em.child(0).attr("title");
				w2 = em.child(1).attr("title");
				if (!w1.equals(w2)) {
					w1 += "转" + w2;
				}
				w1 += win.child(1).text();
				future.setWind(w1);
				futures.add(future);
			}
			weather.setFuture(futures);

			// 指数
			Element zs = doc.getElementById("zs");
			Element ul = zs.getElementsByClass("clearfix").get(0);
			today.setGl(ul.child(0)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setCt(ul.child(1)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setTr(ul.child(2)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setYd(ul.child(3)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setXc(ul.child(4)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setPp(ul.child(5)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setGm(ul.child(6)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setUv(ul.child(7)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());
			today.setCo(ul.child(8)
					.getElementsByAttributeValue("data-dn", "7d1").get(0)
					.child(0).text());

			weather.setToday(today);
		} catch (IOException e) {
			// e.printStackTrace();
			log.info(e.getMessage());
			return null;
		}

		return new Gson().toJson(weather);
	}

	/**
	 * 空气质量
	 * 
	 * @param url
	 * @return
	 */
	public String air(String url) {
		Air air = new Air();
		try {
			Document doc = Jsoup.parse(new URL(url), 3000);
			Element conleft = doc.getElementsByClass("conleft").get(0);
			air.setCity(conleft.child(0).child(0).text());
			Element today = conleft.child(0).child(1);
			air.setAqi(today.child(0).text());
			air.setQuality(today.child(1).child(0).text());
			air.setUpdateTime(today.child(1).child(1).text());

			air.setContent(conleft.child(2).child(1).text());

			Element kqnongdu = doc.getElementsByClass("kqnongdu").get(0);
			air.setPm25(kqnongdu.child(0).child(1).text());
			air.setPm10(kqnongdu.child(1).child(1).text());
			air.setCo(kqnongdu.child(2).child(1).text());
			air.setNo2(kqnongdu.child(3).child(1).text());
			air.setO3(kqnongdu.child(4).child(1).text());
			air.setSo2(kqnongdu.child(5).child(1).text());

		} catch (IOException e) {
			// e.printStackTrace();
			log.info(e.getMessage());
		}
		return new Gson().toJson(air);
	}

	/**
	 * 油价
	 * 
	 * @param url
	 * @return
	 */
	public String oil(String url) {

		List<Oil> oils = new ArrayList<Oil>();
		try {
			Document doc = Jsoup.parse(new URL(url), 3000);

			Element oilTable = doc.getElementsByClass("oilTable").get(0);
			Element tbody = oilTable.child(1);
			// 所有tr
			Elements trs = tbody.children();
			for (Element tr : trs) {
				Oil oil = new Oil();
				oil.setCity(tr.child(0).text());
				oil.setGas90(tr.child(1).text());
				oil.setGas93(tr.child(2).text());
				oil.setGas97(tr.child(3).text());
				oil.setDie(tr.child(4).text());
				oils.add(oil);
				oil = new Oil();
				String city = tr.child(5).text().replace(" ", "");
				if ("".equals(city)) {
					break;
				}
				oil.setCity(city);
				oil.setGas90(tr.child(6).text());
				oil.setGas93(tr.child(7).text());
				oil.setGas97(tr.child(8).text());
				oil.setDie(tr.child(9).text());
				oils.add(oil);
			}

		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return new Gson().toJson(oils);
	}

	/**
	 * 黄金数据
	 * 
	 * @param url
	 * @return
	 */
	public String gold(String url) {

		List<Gold> glods = new ArrayList<Gold>();
		try {
			Document doc = Jsoup.parse(new URL(url), 3000);
			Element table = doc.getElementsContainingText("上海黄金交易所行情").parents().parents().parents().last();
			Elements trs = table.getElementsByClass("r");
			for (Element tr : trs) {
				if ("tr".equals(tr.tagName())) {
					Gold gold = new Gold();
					gold.setVariety(tr.child(0).text());
					gold.setLastestpri(tr.child(1).text());
					gold.setOpenpri(tr.child(2).text());
					gold.setMaxpri(tr.child(3).text());
					gold.setMinpri(tr.child(4).text());
					gold.setLimit(tr.child(5).text());
					gold.setYespri(tr.child(6).text());
					gold.setTotalvol(tr.child(7).text());
					gold.setTime(tr.child(8).text());
					glods.add(gold);
				}
			}

		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return new Gson().toJson(glods);
	}
	/**
	 * 货币汇率
	 * @param url
	 * @return
	 */
	public String rmbquot(String url){
		UrlWebContentUtils webContent = null;
		String[] urls = url.split(";");

		List<Rmbquot> rmbquots = new ArrayList<Rmbquot>();
		List<Rmbquot> showDatalist = null;
		List<Rmbquot.RefePrice> refePrice = null;
		Gson gson = new Gson();
		for(String u : urls){
			String[] fun = u.split("=");
			webContent = new UrlWebContentUtils("GBK");
			String content = webContent.getOneHtml(u).replace(fun[1]+"(", "").replace(")", "");
			if("ShowDatalist".equals(fun[1])){
				showDatalist = gson.fromJson(content,  new TypeToken<List<Rmbquot>>(){}.getType());
			}else{
				refePrice = gson.fromJson(content,  new TypeToken<List<Rmbquot.RefePrice>>(){}.getType());
			}
		}
		for(Rmbquot r : showDatalist){
			if("中国银行".equals(r.getBank())){
				for(RefePrice ref : refePrice){
					if(ref.getCode().equals(r.getCode())){
						r.setRefePrice(ref.getRefePrice());
						break;
					}
				}
				rmbquots.add(r);
			}
		}
		return new Gson().toJson(rmbquots);
	}

	public static void main(String[] args) {
		String url = "http://data.bank.hexun.com/other/cms/foreignexchangejson.ashx?callback=ShowDatalist;http://data.bank.hexun.com/other/cms/fxjhjson.ashx?callback=PereMoreData";
		JsoupHelper helper = new JsoupHelper();
		System.out.println(helper.rmbquot(url));
	}
}
