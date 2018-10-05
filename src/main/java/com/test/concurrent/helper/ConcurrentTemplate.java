package com.test.concurrent.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * 模拟并发测试模板类
 * @author wxh
 */
@Slf4j
public abstract class ConcurrentTemplate {

    /**
     * 并发请求总数
     */
    public static int clientTotal = 10000;

    /**
     * 同时并发执行线程数
     */
    public static int threadTotal = 500;


    public void doConcurrentRun() throws Exception{
        this.doConcurrentRun(clientTotal ,threadTotal);
    }

    /**
     * 模拟并发执行
     * @param clientTotal 并发请求总数
     * @param threadTotal 允许同时并发执行线程数（限流）
     */
    public void doConcurrentRun(int clientTotal,int threadTotal) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        long beginTime = System.currentTimeMillis();
        log.info("beginTime = {}", beginTime);

        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    doHandle();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("执行业务异常", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        log.info("time consuming = {}", System.currentTimeMillis() - beginTime);

        doAfter();

    }

    /**
     * 核心并发业务
     */
    public abstract void doHandle();

    /**
     * 并发后处理
     */
    public abstract void doAfter();


}
