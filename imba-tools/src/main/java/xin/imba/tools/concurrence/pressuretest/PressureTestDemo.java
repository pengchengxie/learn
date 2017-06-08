package xin.imba.tools.concurrence.pressuretest;


import java.io.IOException;

/**
 * 并发测试demo类
 * Created by xiepengcheng on 2016/8/22.
 */
public class PressureTestDemo extends BasePressureTest {
    public static void main(String[] args) {
        final PressureTestDemo localPressure = new PressureTestDemo();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < THREAD_REQUEST_COUNT; j++) {
                        try {
                            Thread.sleep(THREAD_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        String requestStr = "在这里添加请求参数";
                        String responseStr = "在这里添加请求逻辑";

                        localPressure.writeLog("请求：" + requestStr + "\n" + "返回：" + responseStr + "\n");
                    }
                }
            }.start();
        }

        // 保证前面的线程都执行完
        while (localPressure.inc < THREAD_COUNT * THREAD_REQUEST_COUNT) {
            Thread.yield();
        }
        System.out.println(localPressure.inc);
        try {
            fileWritter.flush();
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("并发测试完毕...");
    }
}
