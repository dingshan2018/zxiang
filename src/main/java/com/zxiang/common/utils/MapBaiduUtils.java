package com.zxiang.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MapBaiduUtils {

	/** 调用百度地图的秘钥 AK */
	public static final String BAIDU_AK="v6pmzzGrOiZaBInww8m5oPFWE5qkytd3";
	/** 调用百度地图的安全码 */
	public static final String MCODE="9C:59:FF:5F:A1:5E:F4:7B:67:F0:E1:F6:2E:63:DB:6F:DE:1A:96:91;com.great.baidumap";
	
	/**
	 * 通过地址获取百度地图的经纬度
	 * @param key	百度API秘钥
	 * @param addr
	 * @return 经纬度信息
	 * 参考文章  https://www.cnblogs.com/mfmdaoyou/p/6868307.html
	 */
	public static Map<String, BigDecimal> getLatAndLngByAddress(String key,String addr) throws Exception{
        String address = "";
        String lat = "";
        String lng = "";
        InputStreamReader insr = null;
        try {
			address = java.net.URLEncoder.encode(addr,"UTF-8");  
			
			String url = String.format("http://api.map.baidu.com/geocoder/v2/?ak="+key+"&output=json&mcode="+MCODE+"&address=%s",address);
			URL myURL = null;
			URLConnection httpsConn = null;  
			//进行转码
			myURL = new URL(url);
			
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
			    insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
			    BufferedReader br = new BufferedReader(insr);
			    String data = null;
			    if ((data = br.readLine()) != null) {
			        lat = data.substring(data.indexOf("\"lat\":") 
			        + ("\"lat\":").length(), data.indexOf("},\"precise\""));
			        lng = data.substring(data.indexOf("\"lng\":") 
			        + ("\"lng\":").length(), data.indexOf(",\"lat\""));
			    }
			    insr.close();
			}
			
			Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
			map.put("lat", new BigDecimal(lat));
			map.put("lng", new BigDecimal(lng));
			return map;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if(insr != null){
				try {
					insr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		String address = "福建省福州市宝龙";
		Map<String, BigDecimal> baiduMap = null;

		try {
			//TODO 腾讯地图和百度地图的经纬度还是有明显偏差的；且腾讯地图位置需要规范精确否则查询失败
			baiduMap = getLatAndLngByAddress(BAIDU_AK,address);
			System.out.println("经度："+baiduMap.get("lng")+"--- 纬度："+baiduMap.get("lat"));
			
			MapTencentUtils.getLatAndLngByAddress(MapTencentUtils.KEY, address);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/**
		 * @param address
		 * @return
		 * 参考文章：https://blog.csdn.net/ghjzzhg/article/details/78193739
		 */
		//getLngAndLat(address);
	}
}
