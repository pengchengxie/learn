package xin.imba.commons.handler;

import xin.imba.commons.util.encrypt.RSAUtil;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Handler - RSA助手类
 * Created by xiepengcheng on 2017/5/20.
 */
public class RSAHandler {
    /**
     * 公钥加密
     *
     * @param publicKey
     * @param data
     * @return
     */
    public static String encrypt(PublicKey publicKey, String data) {
        byte[] bResult = RSAUtil.encrypt(publicKey, data.getBytes());
        return new String(bResult);
    }

    /**
     * 私钥解密
     *
     * @param privateKey
     * @param data
     * @return
     */
    public static String decrypt(PrivateKey privateKey, String data) {
        byte[] bResult = RSAUtil.decrypt(privateKey, data.getBytes());
        return new String(bResult);
    }

}
