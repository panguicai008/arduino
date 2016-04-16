package com.arduino.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public class PhoneCodeUtil {
	public final static String UID = "";
	public final static String KEY = "";

	public static void sendCode(String phone, String text) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", UID), new NameValuePair("Key", KEY),
				new NameValuePair("smsMob", phone), new NameValuePair("smsText", text) };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
		System.out.println(result); // 打印返回消息状态
		post.releaseConnection();
	}

}
