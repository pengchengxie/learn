package xin.imba.commons.util.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

/**
 * Util - AES加密算法工具类
 *
 * @author : xiepengcheng 2016年4月11日 下午4:12:14
 */
public class AESUtil {

    /**
     * 预设的Initialization Vector，为16 Bits的0
     */
    private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    /**
     * 加密算法使用AES
     */
    private static final String ALGORITHM = "AES";
    /**
     * AES使用CBC模式与PKCS5Padding
     */
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    /**
     * 取得AES加解密的密钥
     */
    private Key key;
    /**
     * AES CBC模式使用的Initialization Vector
     */
    private IvParameterSpec iv;
    /**
     * Cipher
     */
    private Cipher cipher;

    /**
     * 使用128 Bits的AES秘钥（计算任意长度密钥的MD5)和预设IV
     *
     * @param key 传入任意长度的AES秘钥
     */
    public AESUtil(final String key) {
        this(key, 128);
    }

    /**
     * 使用128 Bits或是256 Bits的AES秘钥（计算任意长度密钥的MD5或是SHA256)和预设IV
     *
     * @param key 传入入任意长度的AES密钥
     * @param bit 传入AES密钥长度，数值可以是128、256 (Bits)
     */
    public AESUtil(final String key, final int bit) {
        this(key, bit, null);
    }

    /**
     * 使用128 Bits或是256 Bits的AES密钥(计算任意长度密钥的MD5或是SHA256)，用MD5计算IV值
     *
     * @param key 传入任意长度的AES密钥
     * @param bit 传入AES密钥长度，数值可以是128、256 (Bits)
     * @param iv  传入任意长度的IV字串
     */
    public AESUtil(final String key, final int bit, final String iv) {
        if (bit == 256) {
            this.key = new SecretKeySpec(getHash("SHA-256", key), ALGORITHM);
        } else {
            this.key = new SecretKeySpec(getHash("MD5", key), ALGORITHM);
        }
        if (iv != null) {
            this.iv = new IvParameterSpec(getHash("MD5", iv));
        } else {
            this.iv = DEFAULT_IV;
        }

        init();
    }


    /**
     * 获取字串的hash值
     *
     * @param algorithm
     * @param text
     * @return
     */
    private static byte[] getHash(final String algorithm, final String text) {
        try {
            return getHash(algorithm, text.getBytes("UTF-8"));
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 获取字串的hash值
     *
     * @param algorithm
     * @param data
     * @return
     */
    private static byte[] getHash(final String algorithm, final byte[] data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(data);
            return digest.digest();
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 加密方法
     *
     * @param str
     * @return
     */
    public String encrypt(final String str) {
        try {
            return encrypt(str.getBytes("UTF-8"));
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 加密方法
     *
     * @param data
     * @return
     */
    public String encrypt(final byte[] data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            final byte[] encryptData = cipher.doFinal(data);
            return new String(Base64.encodeBase64(encryptData), "UTF-8");
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 解密方法
     *
     * @param str
     * @return
     */
    public String decrypt(final String str) {
        try {
            return decrypt(Base64.decodeBase64(str));
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 解密方法
     *
     * @param data
     * @return
     */
    public String decrypt(final byte[] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            final byte[] decryptData = cipher.doFinal(data);
            return new String(decryptData, "UTF-8");
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
