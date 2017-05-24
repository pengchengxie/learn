package xin.imba.commons.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * <b>简述：文件操作公共类</b>
 *
 * @author : xiepengcheng 2016年12月5日 下午9:33:41
 */
public final class FileUtil {

    /**
     * <b>简述：判断文件是否合法</b>
     *
     * @param contentType 文件名
     * @param allowTypes  文件扩展名list
     * @return
     */
    public static boolean isValid(String contentType, String... allowTypes) {
        if (null == contentType || "".equals(contentType)) {
            return false;
        }
        for (String type : allowTypes) {
            if (contentType.indexOf(type) > -1) {
                return true;
            }
        }
        return false;
    }


    /**
     * <b>简述：获取文件名的扩展名</b>
     *
     * @param contentType 文件名
     * @return
     */
    public static String getExtName(String contentType) {
        return contentType.substring(contentType.lastIndexOf(".") + 1);
    }


    /**
     * <b>简述：字节数组转输入流</b>
     *
     * @param buf
     * @return
     */
    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }


    /**
     * <b>简述：输入流转字节数组</b>
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    public static byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}