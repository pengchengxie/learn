package xin.imba.commons.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 字符串工具类
 * <p>
 * Created by xiepengcheng on 2017/4/24.
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null != str) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 分割 String 字符串为 String 数组
     * 目前为自行实现的
     *
     * @param str
     * @param separatorChars
     * @return
     */
    public static String[] splitString(String str, String separatorChars) {
        String[] strings = new String[0];
        if (isNotEmpty(str)) {
            strings = StringUtils.split(str, separatorChars);
        }
        return strings;
    }

    /**
     * 获取唯一ID，策略为UUID去掉“-”
     * @return
     */
    public static String getUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
