package xin.imba.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * .properties文件读取工具类
 * <p>
 * Created by xiepengcheng on 2017/4/24.
 */
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     *
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (null == is) {
                throw new FileNotFoundException(fileName + "file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取字符属性值（默认为空字符串）
     *
     * @param props
     * @param key
     * @return
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取字符属性值（可指定默认值）
     *
     * @param props
     * @param key
     * @param defualtValue
     * @return
     */
    public static String getString(Properties props, String key, String defualtValue) {
        String value = defualtValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值属性值（默认为0）
     *
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取数值属性值（可指定默认值）
     *
     * @param props
     * @param key
     * @param defualtValue
     * @return
     */
    public static int getInt(Properties props, String key, int defualtValue) {
        int value = defualtValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取字符属性值（默认为0）
     *
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取数值属性值（可指定默认值）
     *
     * @param props
     * @param key
     * @param defualtValue
     * @return
     */
    public static boolean getBoolean(Properties props, String key, boolean defualtValue) {
        boolean value = defualtValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
