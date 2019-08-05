package com.zxiang.project.advertise.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ZXIANG on 2017/7/21.
 */
public class MapUtil {
    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}

class MapKeyComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}