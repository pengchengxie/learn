package xin.imba.commons.handler;

import xin.imba.commons.util.encrypt.AESUtil;
import xin.imba.commons.util.encrypt.MD5Util;

/**
 * Handler - AES助手类
 * Created by xiepengcheng on 2017/5/20.
 */
public class AESHandler {

    /**
     * 获取加密后的结果
     *
     * @param apiKey
     * @param secret
     * @param salt
     * @param data
     * @return
     */
    public static String encrypt(String apiKey, String secret, String salt, String data) {
        AESUtil aesUtil = getAESUtil(apiKey, secret, salt, data);
        return aesUtil.encrypt(data);
    }

    /**
     * 获取解密后的结果
     *
     * @param apiKey
     * @param secret
     * @param salt
     * @param data
     * @return
     */
    public static String decrypt(String apiKey, String secret, String salt, String data) {
        AESUtil aesUtil = getAESUtil(apiKey, secret, salt, data);
        return aesUtil.decrypt(data);
    }

    /**
     * 获取AESUtil
     *
     * @param apiKey
     * @param secret
     * @param salt
     * @param data
     * @return
     */
    public static AESUtil getAESUtil(String apiKey, String secret, String salt, String data) {
        String key = MD5Util.MD5(apiKey + secret + salt); // 生成密钥
        return new AESUtil(key);
    }
}
