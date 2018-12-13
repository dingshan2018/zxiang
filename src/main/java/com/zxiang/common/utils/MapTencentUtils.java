package com.zxiang.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MapTencentUtils {
	 // key，需要在腾讯地图申请一个KEY
    public static final String KEY = "34RBZ-FDK3O-JL5WH-S5EUP-UD5HQ-O3FF7";
 
    /**
     * @param key 腾讯开发者秘钥
     * @param addr 查询地址(注：地址中请包含城市名称，否则会影响解析效果)
     * @return
     * @throws IOException 
     */
    public static Map<String, BigDecimal> getLatAndLngByAddress(String key,String addr) throws Exception {
    	 
    	String address = "";
        String lat = "";
        String lng = "";
        String data = "";
        InputStreamReader insr = null;
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        
        try {
			address = java.net.URLEncoder.encode(addr,"UTF-8");  
			
			String url = "https://apis.map.qq.com/ws/geocoder/v1/?address==" + address + "&key=" + key;
			URL myURL = null;
			URLConnection httpsConn = null;  
			//进行转码
			myURL = new URL(url);
			
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
			    insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
			    BufferedReader br = new BufferedReader(insr);
			    String line;
			    while ((line = br.readLine()) != null) {
			    	data += line + "\n";
			    }
			    insr.close();
			}
			
			//System.out.println("data:=========>>> "+data);
			JSONObject returnInfoJson = (JSONObject) JSON.parse(data);
			int status = (int) returnInfoJson.get("status");
			if(status == 0){
				JSONObject resultJson = (JSONObject) returnInfoJson.get("result");
				JSONObject location = (JSONObject) resultJson.get("location");
				lat = location.getString("lat");
				lng = location.getString("lng");
				map.put("lat", new BigDecimal(lat));
			    map.put("lng", new BigDecimal(lng));
			    System.out.println("经度：" + lng + "--- 纬度：" + lat);
			}else{
				throw new Exception("查询经纬度失败!");
			}
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
        // 测试
    	try {
			getLatAndLngByAddress(KEY, "福建省福州市仓山区");
			MapBaiduUtils.getLatAndLngByAddress(MapBaiduUtils.BAIDU_AK, "福建省福州市仓山区");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
