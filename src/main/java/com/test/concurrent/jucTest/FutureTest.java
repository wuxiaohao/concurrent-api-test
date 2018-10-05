package com.test.concurrent.jucTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wxh
 */
@Slf4j
public class FutureTest {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do call");
            Thread.sleep(1000);
            return "end call";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Thread.sleep(100);
        log.info("do main");
        String result = future.get();
        log.info("result：{}", result);
    }

}
