package xin.imba.commons.util.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Util - RSA加密算法工具类
 *
 * @author : xiepengcheng 2016年10月19日 下午2:32:02
 */
public final class RSAUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 加密/解密算法
     */
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    /**
     * 安全服务提供者
     */
    private static final Provider PROVIDER = new BouncyCastleProvider();

    /**
     * 不可实例化
     */
    private RSAUtil() {
    }

    /**
     * 生成密钥对
     *
     * @param keySize 密钥大小
     * @return 密钥对
     */
    public static KeyPair generateKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范
     *
     * @param encodedKey 密钥编码
     * @return 私钥
     */
    public static PrivateKey generatePrivateKey(byte[] encodedKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 还原私钥
     *
     * @param keyString 密钥字符串(BASE64编码)
     * @return 私钥
     */
    public static PrivateKey generatePrivateKey(String keyString) {
        return generatePrivateKey(Base64.decodeBase64(keyString));
    }

    /**
     * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
     *
     * @param encodedKey 密钥编码
     * @return 公钥
     */
    public static PublicKey generatePublicKey(byte[] encodedKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, PROVIDER);
            return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 还原公钥
     *
     * @param keyString 密钥字符串(BASE64编码)
     * @return 公钥
     */
    public static PublicKey generatePublicKey(String keyString) {
        return generatePublicKey(Base64.decodeBase64(keyString));
    }


    /**
     * 加密，三步走
     *
     * @param publicKey 公钥
     * @param data      数据
     * @return 密文
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION, PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 解密，三步走
     *
     * @param privateKey 私钥
     * @param data       数据
     * @return 明文
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION, PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取密钥字符串
     *
     * @param key 密钥
     * @return 密钥字符串(BASE64编码)
     */
    public static String getKeyString(Key key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 获取密钥
     *
     * @param type        类型
     * @param inputStream 输入流
     * @param password    密码
     * @return 密钥
     */
    public static Key getKey(String type, InputStream inputStream, String password) {
        try {
            KeyStore keyStore = KeyStore.getInstance(type, PROVIDER);
            keyStore.load(inputStream, password != null ? password.toCharArray() : null);
            String alias = keyStore.aliases().hasMoreElements() ? keyStore.aliases().nextElement() : null;
            return keyStore.getKey(alias, password != null ? password.toCharArray() : null);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取证书
     *
     * @param type        类型
     * @param inputStream 输入流
     * @return 证书
     */
    public static Certificate getCertificate(String type, InputStream inputStream) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(type, PROVIDER);
            return certificateFactory.generateCertificate(inputStream);
        } catch (CertificateException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 生成签名，三步走
     * 1. 实例化，传入算法
     * 2. 初始化，传入私钥
     * 3. 签名
     *
     * @param algorithm  签名算法
     * @param privateKey 私钥
     * @param data       数据
     * @return 签名
     */
    public static byte[] sign(String algorithm, PrivateKey privateKey, byte[] data) {
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     * 1. 实例化，传入算法
     * 2. 初始化，传入公钥
     * 3. 验签
     *
     * @param algorithm 签名算法
     * @param publicKey 公钥
     * @param sign      签名
     * @param data      数据
     * @return 是否验证通过
     */
    public static boolean verify(String algorithm, PublicKey publicKey, byte[] sign, byte[] data) {
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     *
     * @param algorithm   签名算法
     * @param certificate 证书
     * @param sign        签名
     * @param data        数据
     * @return 是否验证通过
     */
    public static boolean verify(String algorithm, Certificate certificate, byte[] sign, byte[] data) {
        try {
            Signature signature = Signature.getInstance(algorithm, PROVIDER);
            signature.initVerify(certificate);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (SignatureException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


}