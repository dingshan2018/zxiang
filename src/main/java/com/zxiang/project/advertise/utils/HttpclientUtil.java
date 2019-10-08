package com.zxiang.project.advertise.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.zxiang.project.advertise.adSchedule.controller.AdScheduleController;

public class HttpclientUtil {
	
	public static final String CHAR_ENCODING = "UTF-8";
	public static final Logger logger = Logger.getLogger(HttpclientUtil.class);
	public static String upload(String postUrl, List<MultipartFile> files, Map<String, String> param, Map<String, String> header) {
	       logger.info("*****************request*****************");
	       CloseableHttpClient httpClient = null;
	       CloseableHttpResponse response = null;
//	       MultipartEntityBuilder builder = MultipartEntityBuilder.create();;
	       MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
	       String url = postUrl;
	       String rst = "";
	       try {
	           httpClient = HttpClients.createDefault();
	           logger.info("请求路径： "+ url);
	           HttpPost httpPost = new HttpPost(url);
	           for(MultipartFile multipartFile:files){
	               InputStream ins = multipartFile.getInputStream();
	               builder.addBinaryBody("Filedata", ins, ContentType.DEFAULT_BINARY, multipartFile.getOriginalFilename());
	           }
	           if(param!=null && param.size()>0) {
	        	   for (Map.Entry<String, String> entry : param.entrySet()) {
		               String key = entry.getKey();
		               String value = entry.getValue();
		               StringBody stringBody = new StringBody(value, ContentType.create(
		                       "text/plain", Consts.UTF_8));
		               builder.addPart(key, stringBody);
		               logger.info("请求参数："+key+":"+ value);
		           }
	           }
	           
	           HttpEntity reqEntity = builder.build();
	           httpPost.setEntity(reqEntity);
	           if(header!=null && header.size()>0) {
	        	   for (Map.Entry<String, String> entry : header.entrySet()) {
		               String key = entry.getKey();
		               String value = entry.getValue();
		               httpPost.setHeader(key, value);
		               logger.info("请求头："+key+":"+ value);
		           }
	           }
	           
	           // 发起请求 并返回请求的响应
	           response = httpClient.execute(httpPost);
	           // 获取响应对象
	           HttpEntity resEntity = response.getEntity();
	           if (resEntity != null) {
	               // 打印响应内容
	               rst = EntityUtils.toString(resEntity, Charset.forName("UTF-8")) ;
	               logger.info("*****************response*****************");
	               logger.info("响应结果： " +rst);
	           }
	           // 销毁
	           EntityUtils.consume(resEntity);
	           return rst;
	       } catch (Exception e) {
	           logger.info("出错啦： "+e.getMessage());
	           e.printStackTrace();
	           return "出错了";
	       } finally {
	           try {
	               if (response != null) {
	                   response.close();
	               }
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	           try {
	               if (httpClient != null) {
	                   httpClient.close();
	               }
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	       }
	   }
    
}