package com.zxiang.project.advertise.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.RequestEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxiang.project.advertise.utils.https.MySecureProtocolSocketFactory;

public class Tools {
	
	public static final String CHAR_ENCODING = "UTF-8";
	
	/*public static String calSign(String uri, String method, Map<String, String> params, String appKey) throws Exception{
		String uri_encode = URLEncoder.encode(uri, CHAR_ENCODING);
		List<String> keys = new ArrayList<String>();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(!"sig".equals(key)){
				keys.add(key);
			}
		}
		
		Collections.sort(keys);
		StringBuilder paramString = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String keysi = keys.get(i);
			paramString.append(keysi).append("=").append(params.get(keysi)).append("&");
		}
		
		if(paramString.length() > 0){
			paramString.deleteCharAt(paramString.length() - 1);
		}
		
		String paramString_encode = URLEncoder.encode(paramString.toString(), CHAR_ENCODING);
		
		StringBuilder source = new StringBuilder();
		source.append(method.toUpperCase()).append("&");
		source.append(uri_encode).append("&");
		source.append(paramString_encode);
		
		String signKey = appKey + "&";
		byte[] enc = EncryptUtil.getHmacSHA1(source.toString(), signKey);
		String sign = Base64.encode(enc, false);
		return sign;
	}*/
	
	// 获取UUID
	public static String getUUID() {
		String key = UUID.randomUUID().toString();
		String[] keys = key.split("-");
		StringBuffer sb = new StringBuffer();
		for (String k : keys) {
			sb.append(k);
		}
		
		return sb.toString();
	}
	
	public static String paramsToString(Map<String, String> paramsMap) {
		StringBuilder url = new StringBuilder();
		Iterator<Entry<String, String>> it = paramsMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> entry = it.next();
			url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		
		if(url.length() > 0){
			url.deleteCharAt(url.length() - 1);
		}
		
		return url.toString();
	}
	
	/**
	 * HTTP post请求
	 * Content-Type 为application/json
	 * @param uri
	 * @param requst
	 * @return
	 * @throws IOException
	 */
	public static String doPost(String uri, String requst) throws IOException {
		String restult =null;
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient();
		// 使用POST方法
		PostMethod method = new PostMethod(uri);
		try {
			StringRequestEntity entity = new StringRequestEntity(requst, "application/json", "UTF-8");
			method.setRequestEntity(entity);
			client.executeMethod(method);

			InputStream inputStream = method.getResponseBodyAsStream();
			restult = IOUtils.toString(inputStream);
			return restult;
		}finally {
			// 释放连接
			method.releaseConnection();
		}
	}
	
	/**
	 * HTTP post请求
	 * Content-Type 为application/x-www-form-urlencoded;charset=UTF-8
	 * @param uri
	 * @param requst
	 * @return
	 * @throws IOException
	 */
	public static String doPostForm(String uri, String requst) throws IOException {
		String restult =null;
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient();
		// 使用POST方法
		PostMethod method = new PostMethod(uri);
		try {
			StringRequestEntity entity = new StringRequestEntity(requst, "application/x-www-form-urlencoded", "UTF-8");
			method.setRequestEntity(entity);
			client.executeMethod(method);

			InputStream inputStream = method.getResponseBodyAsStream();
			restult = IOUtils.toString(inputStream);
			return restult;
		}finally {
			// 释放连接
			method.releaseConnection();
		}
	}
	
	/**
	 * HTTP post请求
	 * Content-Type 为multipart/form-data
	 * @param uri
	 * @param requst
	 * @return
	 * @throws IOException
	 */
	public static String doPostMultipart(String uri, String requst) throws IOException {
		String restult =null;
		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient();
		// 使用POST方法
		PostMethod method = new PostMethod(uri);
		try {
			StringRequestEntity entity = new StringRequestEntity(requst, "multipart/form-data", "UTF-8");
			method.setRequestEntity(entity);
			client.executeMethod(method);

			InputStream inputStream = method.getResponseBodyAsStream();
			restult = IOUtils.toString(inputStream);
			return restult;
		}finally {
			// 释放连接
			method.releaseConnection();
		}
	}
	
	/**
     * 根据返回的String分析调用结果
     * @param result
     * @return 结果
     */
    public static AdHttpResult analysisResult(String result) {
        JSONObject returnInfoJson = (JSONObject) JSON.parse(result);
        String code = returnInfoJson.getString("code");
        String message = returnInfoJson.getString("message");
        Map<String, Object> returnInfoMap = json2Map(returnInfoJson);
        	
        AdHttpResult analysisStatus = new AdHttpResult(code, message);
        
        analysisStatus.putAll(returnInfoMap);
        
        return analysisStatus;
    }
    
	/** 
     * 将json对象转为Map对象
     * @param json
     * @return Map
     */
    public static Map<String, Object> json2Map(JSONObject json){
    	Map<String, Object> map = new HashMap<String, Object>();  
        for(Object k : json.keySet()){    
            Object v = json.get(k);     
            if(v instanceof JSONArray){    
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();    
                Iterator<Object> it = ((JSONArray)v).iterator();    
                while(it.hasNext()){    
                    JSONObject json2 = (JSONObject) it.next();    
                    list.add(json2Map(json2));    
                }    
                map.put(k.toString(), list);    
            } else {    
                map.put(k.toString(), v);    
            }    
        }    
        return map;    
    }
    
}