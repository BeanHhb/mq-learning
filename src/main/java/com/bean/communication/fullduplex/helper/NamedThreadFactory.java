package com.bean.communication.fullduplex.helper;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @Author Bean
 * @Date 2023/2/21 21:30
 */
public class NamedThreadFactory implements ThreadFactory {

    private AtomicInteger idx = new AtomicInteger(0);

    private String prefix;

    public NamedThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + "-" + idx.getAndIncrement());
    }
}
