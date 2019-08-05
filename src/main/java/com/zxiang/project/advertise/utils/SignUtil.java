package com.zxiang.project.advertise.utils;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZXIANG on 2018/6/28.
 */
public class SignUtil {

    public static String createSign(String appkey, Map<String, String> params) throws Exception{
        Map<String, String> map = MapUtil.sortMapByKey(params);
        StringBuffer sb = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String k = it.next();
            String v = (String) map.get(k);
            if (null != v && !"".equals(v)
                    && !"sign".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("appkey=" + appkey);
        String sign = MDUtils.MD5EncodeForHex(sb.toString(),"UTF-8");
        return sign;
    }
    
    public static String createSign(HashMap<String,String> signMap,String appSecrect) throws Exception{
    	return createSign(appSecrect, signMap);
    }

}
