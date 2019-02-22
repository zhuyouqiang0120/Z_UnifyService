package com.zens.unify.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONObject;


public class XmltoJson {
	/**
	 * 转换一个xml格式的字符串到json格式
	 * 
	 * @param xml
	 *            xml格式的字符串
	 * @return 成功返回json 格式的字符串;失败反回null
	 */
	public static String xml2JSON(String xml) {

		// return new XMLSerializer().read(xml).toString();
		JSONObject obj = new JSONObject();
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			obj.put(root.getName(), iterateElement(root));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	/**
	 * 转换一个xml格式的字符串到json格式
	 * 
	 * @param file
	 *            java.io.File实例是一个有效的xml文件
	 * @return 成功反回json 格式的字符串;失败反回null
	 */
	public static String xml2JSON(File file) {
		JSONObject obj = new JSONObject();
		try {
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(file);
			Element root = doc.getRootElement();
			obj.put(root.getName(), iterateElement(root));
			return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 一个迭代方法
	 * 
	 * @param element
	 *            : org.jdom.Element
	 * @return java.util.Map 实例
	 */
	private static Map<String, List<Object>> iterateElement(Element element) {
		List<Element> jiedian = element.getChildren();
		Element et = null;
		Map<String, List<Object>> obj = new HashMap<String, List<Object>>();
		List<Object> list = null;
		for (int i = 0; i < jiedian.size(); i++) {
			list = new LinkedList<Object>();
			et = jiedian.get(i);
			if (et.getTextTrim().equals("")) {
				if (et.getChildren().size() == 0)
					continue;
				if (obj.containsKey(et.getName())) {
					list = obj.get(et.getName());
				}
				list.add(iterateElement(et));
				obj.put(et.getName(), list);
			} else {
				if (obj.containsKey(et.getName())) {
					list = obj.get(et.getName());
				}
				list.add(et.getTextTrim());
				obj.put(et.getName(), list);
			}
		}
		return obj;
	}

	// 测试
	public static void main(String[] args) {
		System.out.println(XmltoJson.xml2JSON("<MapSet>"
				+ "<MapGroup id='Sheboygan'>" + "<Map>"
				+ "<Type>MapGuideddddddd</Type>"
				+ "<SingleTile>true</SingleTile>" + "<Extension>"
				+ "<ResourceId>ddd</ResourceId>" + "</Extension>" + "</Map>"
				+ "<Map>" + "<Type>ccc</Type>" + "<SingleTile>ggg</SingleTile>"
				+ "<Extension>" + "<ResourceId>aaa</ResourceId>"
				+ "</Extension>" + "</Map>" + "<Extension />" + "</MapGroup>"
				+ "<ddd>" + "33333333" + "</ddd>" + "<ddd>" + "444" + "</ddd>"
				+ "</MapSet>"));
	}
}
