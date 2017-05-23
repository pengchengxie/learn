package xin.imba.commons.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Util - JSON工具类
 *
 * @author : xiepengcheng 2016年10月19日 下午2:32:02
 */
public class JsonUtil extends JSON {

    public static String toJSONString(Object object) {
        return toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

}
