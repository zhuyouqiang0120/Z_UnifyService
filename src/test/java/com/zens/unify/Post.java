/**
 * Z_UnifyService com.zens.unify.Post.java
 * 2014年11月12日 下午3:01:12
 * Post
 */
package com.zens.unify;

import java.io.IOException;

import com.zens.unify.utils.HttpClientUtil;
import com.zens.unify.utils.UrlWebContentUtils;

/**
 * 
 * Z_UnifyService com.zens.unify.Post.java
 * Post
 * 2014年11月12日 下午3:01:12
 * @author vector
 *
 */
public class Post {
	
	String url = "http://www.qiche100.cn/";
	public void post(){
		try {
			HttpClientUtil.post();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getSheBao(){
		String url = "http://www.csldbz.gov.cn/fwdt/grsbcx/";
		UrlWebContentUtils urlUtils = new UrlWebContentUtils("utf-8");
		String content = urlUtils.getOneHtml(url);
		
		System.out.println(content);
	}
	
	public static void main(String[] args) {
		Post.getSheBao();
	}
}
