package com.zens.unify.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author vector
 * @time 2014年7月21日 下午6:10:21
 *
 */
public class HttpClientUtil {
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	/**
	 * post提交
	 * 
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public static String get(String uri) {
		/*uri += "?to_station=" + "贵阳";
		uri += "&from_station=" + "上海";
		uri += "&queryDate=" + "2014-7-26";
		uri += "&purpose_codes=ADULT";*/
		// (1) Post请求
		HttpGet get = new HttpGet(uri);
		//设置header
		/*get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; Touch; .NET4.0E; .NET4.0C; Tablet PC 2.0; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; InfoPath.3; rv:11.0) like Gecko");
		get.setHeader("If-Modified-Since", "0");
		get.setHeader("Cache-Control", "no-cache");*/
		get.setHeader("Content-type", "application/x-www-form-urlencoded");
		get.setHeader("Connection", "close");
		get.addHeader("Accept", "text/html");  
		get.addHeader("Accept-Charset", "gb2312"); 
		
		
//		get.set
		// (2) 发送请求
		CloseableHttpClient httpClient = createSSLClientDefault();
		
		StringBuffer result = new StringBuffer();
		// 添加参数
		try {
			HttpResponse response = httpClient.execute(get);
			
			// (3) 处理响应结果
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();

				InputStream in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * 
	 */
	public static void post() throws ClientProtocolException, IOException {
		String getUrl = "http://www.qiche100.cn/function/mobile/home/index.aspx?c=0&p=0";
		getUrl = "http://www.qiche100.cn/";
		String postUrl = "http://www.qiche100.cn/Handler/WZ_AJAX_Server.aspx";

		Header[] headers = new Header[7];
		headers[0] = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; Touch; .NET4.0E; .NET4.0C; Tablet PC 2.0; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; InfoPath.3; rv:11.0) like Gecko");
		headers[1] = new BasicHeader("If-Modified-Since", "0");
		headers[2] = new BasicHeader("Cache-Control", "no-cache");
		headers[3] = new BasicHeader("Content-type", "application/x-www-form-urlencoded");
		headers[4] = new BasicHeader("Connection", "close");
		headers[5] = new BasicHeader("Accept", "text/html");
		headers[6] = new BasicHeader("Accept-Charset", "utf-8");
		
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(getUrl);
		//设置header
		get.setHeaders(headers);
		
		CloseableHttpResponse response = httpClient.execute(get);
	
		String html = EntityUtils.toString(response.getEntity());
		
		
		Document doc = Jsoup.parse(html);
		String keys = doc.getElementById("keys").val();
		
		HttpPost httpPost = new HttpPost(postUrl);
		//设置header
		httpPost.setHeaders(headers);
		
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("GetMethod", "WZQuery"));
		nvps.add(new BasicNameValuePair("keys", keys));
		nvps.add(new BasicNameValuePair("set_vcq_City", "上海"));
		nvps.add(new BasicNameValuePair("set_vcq_Province", "上海市"));
		nvps.add(new BasicNameValuePair("set_vcq_lpn", "鄂A7X388"));
		nvps.add(new BasicNameValuePair("set_vqid", ""));
		nvps.add(new BasicNameValuePair("c", "0"));
		nvps.add(new BasicNameValuePair("p", "0"));
		nvps.add(new BasicNameValuePair("VehicleType", "01"));
		nvps.add(new BasicNameValuePair("Abbr", "鄂"));
		nvps.add(new BasicNameValuePair("Vehicle", "A7X388"));//车牌号
		nvps.add(new BasicNameValuePair("Province", "上海市"));
		nvps.add(new BasicNameValuePair("City", "上海"));
		nvps.add(new BasicNameValuePair("VIN", "G4FA5332990"));//发动机号
		nvps.add(new BasicNameValuePair("EIN", ""));
		nvps.add(new BasicNameValuePair("carBuyYear", "2014"));
		nvps.add(new BasicNameValuePair("insMonthDue", "01"));
		nvps.add(new BasicNameValuePair("accept", "1"));
		nvps.add(new BasicNameValuePair("Phone", ""));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
		
		response = httpClient.execute(httpPost);

		try {
			System.out.println(response.getStatusLine());
			System.out.println(EntityUtils.toString(response.getEntity()));
			
		} finally {
			response.close();
		}
		httpClient.close();

	}
	
	
	public static void getIE(String url) throws ClientProtocolException, IOException{
		String useAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0)";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		get.setHeader("User-Agent", useAgent);
		
		CloseableHttpResponse response = httpClient.execute(get);
	
		String html = EntityUtils.toString(response.getEntity());
		
		System.out.println(html);
	}
	
	
	public static void main(String[] args) throws IOException {
		post();
//		getIE("http://www.cshrss.gov.cn/fwdt/grsbcx");
		/**
		 * 
		 */
	}
}