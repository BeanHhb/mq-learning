package com.bean.communication.fullduplex.helper;

/**
 * @Author Bean
 * @Date 2023/2/21 22:36
 */
public class TimeHelper {
    static long start;
    static long end;

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void end() {
        end = System.currentTimeMillis();
        System.out.println("总计耗时 " + (end - start) / 1000.0 + "s");
    }

}
