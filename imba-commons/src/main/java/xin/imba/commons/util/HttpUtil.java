package xin.imba.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Util - http工具类
 * <p>
 * Created by xiepengcheng on 2017/5/25.
 */
public class HttpUtil {
    /**
     * 缺省连接超时时间
     */
    private static final int DEFAULT_CONN_TIMEOUT = 2000;
    /**
     * 缺省读取超时时间
     */
    private static final int DEFAULT_READ_TIMEOUT = 2000;

    /**
     * 缺省字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * http post请求
     *
     * @param param
     * @param url
     * @return
     */
    public static String doPost(String param, String url) {
        return doPost(param, url, DEFAULT_CHARSET, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    /**
     * http post请求
     *
     * @param param
     * @param url
     * @param codeType
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public static String doPost(String param, String url, String codeType, int connectTimeout, int readTimeout) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "token");

            out = new OutputStreamWriter(conn.getOutputStream(), codeType);
            out.write(param);
            out.flush();
            out.close();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), codeType));
            String line = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
