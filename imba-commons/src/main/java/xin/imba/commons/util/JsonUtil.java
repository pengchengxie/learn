package xin.imba.commons.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Util - JSON工具类
 *
 * @author : xiepengcheng 2016年10月19日 下午2:32:02
 */
public final class JsonUtil extends JSON {

    public static String toJSONString(Object object) {
        return toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 去掉json中变量的下划线，除首字母之外的其他字母大写
     *
     * @param json
     * @return
     */
    public static String decodeJSONObject(JSONObject json) {
        JSONObject result = new JSONObject();
        if (null != json) {
            Set<String> keySet = json.keySet();
            Iterator<String> keys = keySet.iterator();

            Object o;
            String key;
            String[] splitKey = null;
            while (keys.hasNext()) {
                String parseKey = "";
                key = keys.next();
                o = json.get(key);

                splitKey = key.split("_");
                if (splitKey.length > 1) {
                    for (int i = 0; i < splitKey.length; i++) {
                        if (i > 0) {
                            String fristWord = splitKey[i].substring(0, 1);
                            parseKey += splitKey[i].replaceFirst(fristWord, fristWord.toUpperCase());
                        } else {
                            parseKey += splitKey[i];
                        }
                    }
                    result.put(parseKey, o);
                } else {
                    result.put(key, o);
                }
            }
        }
        return result.toString();
    }

    /**
     * 将json转换成map
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject json) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Set<String> keySet = json.keySet();
        Iterator<String> keys = keySet.iterator();
        Object o;
        String key;
        while (keys.hasNext()) {
            key = keys.next();
            o = json.get(key);
            jsonMap.put(key, o);
        }
        return jsonMap;
    }

}
