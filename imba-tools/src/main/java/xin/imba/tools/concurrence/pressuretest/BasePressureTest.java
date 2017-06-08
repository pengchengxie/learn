package xin.imba.tools.concurrence.pressuretest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 并发测试基类
 * Created by xiepengcheng on 2016/8/22..
 */
public class BasePressureTest {
    /**
     * 并发线程数
     */
    protected static final int THREAD_COUNT = 100;

    /**
     * 单个线程的请求次数
     */
    protected static final int THREAD_REQUEST_COUNT = 1000;

    /**
     * 单个线程请求时间间隔
     */
    protected static final int THREAD_SLEEP_TIME = 8;

    /**
     * 日志文件存储路径
     */
    protected static final String FILE_PATH = "D:/temp/log/api/";

    /**
     * 总请求次数统计
     */
    protected int inc = 0;

    /**
     * 日志文件写入
     */
    protected static FileWriter fileWritter = null;

    static {
        try {
            // 当文件夹不存在时，自动创建多层目录
            File file = new File(FILE_PATH);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            fileWritter = new FileWriter(FILE_PATH + "api_test_log_" + System.currentTimeMillis() + ".txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>简述：计算执行次数并记录日志</b>
     */
    protected synchronized void writeLog(String log) {
        inc++;
        try {
            fileWritter.write(log);
            if (0 == inc % 100) {
                fileWritter.flush();
                System.out.println(inc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
