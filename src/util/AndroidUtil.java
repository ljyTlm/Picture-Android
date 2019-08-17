package util;

import Interface.Notice;

import java.io.IOException;
import java.io.InputStream;

public class AndroidUtil {

    static String cmd = "cmd /c ";

    /**
     * 默认为本机mumu安卓模拟器，
     */
    static {
        runAdb("adb connect 127.0.0.1:7555");
    }

    /**
     *
     * @param num 截图数量
     * @param time 截图间隔
     * @param savePath 保存地址
     * @param n 通知
     */
    static void getScreenCap(int num, int time, String savePath, Notice n) {
        final Notice notice = n;
        new Thread() {
            @Override
            public void run() {

            }
        };
    }

    /**
     * 点击屏幕位置
     * @param x
     * @param y
     */
    static void inputTabScreen(int x, int y) {
        String adb = "adb shell input tap "+x+" "+y;
        runAdb(adb);
    }

    /**
     * 连接安卓手机
     * @param ip
     * @param port
     */
    static void connectAndroid(String ip, String port) {
        String adb = "adb connect "+ip+":"+port;
        runAdb(adb);
    }

    /**
     * 执行adb命令
     * @param adb
     * @return
     */
    static InputStream runAdb(String adb) {
        try {
            Process exec = Runtime.getRuntime().exec(cmd+adb);
            return exec.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
