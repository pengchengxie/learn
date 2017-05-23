package xin.imba.commons.util;


/**
 * 数据类型转型操作工具类
 * <p>
 * Created by xiepengcheng on 2017/4/24.
 */
public final class CastUtil {

    /**
     * 转为String型
     *
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为String型（提供默认值）
     *
     * @param obj
     * @param defualtValue
     * @return
     */
    public static String castString(Object obj, String defualtValue) {

        return obj != null ? String.valueOf(obj) : defualtValue;
    }

    /**
     * 转为double 型
     *
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转为double型（提供默认值）
     *
     * @param obj
     * @param defualtValue
     * @return
     */
    public static double castDouble(Object obj, double defualtValue) {
        double doubleValue = defualtValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defualtValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为long型
     *
     * @param obj
     * @return
     */
    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为long型（提供默认值）
     *
     * @param obj
     * @param defualtValue
     * @return
     */
    public static long castLong(Object obj, long defualtValue) {
        long longValue = defualtValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defualtValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为int型
     *
     * @param obj
     * @return
     */
    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为int型（提供默认值）
     *
     * @param obj
     * @param defualtValue
     * @return
     */
    public static int castInt(Object obj, int defualtValue) {
        int intValue = defualtValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defualtValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为boolean型
     *
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * 转为int型（提供默认值）
     *
     * @param obj
     * @param defualtValue
     * @return
     */
    public static boolean castBoolean(Object obj, boolean defualtValue) {
        boolean booleanValue = defualtValue;
        if (null != obj) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
