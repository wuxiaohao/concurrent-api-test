package com.test.concurrent.jucTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author wxh
 */
@Slf4j
public class FutureTaskTest {

    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            log.info("do task");
            Thread.sleep(1000);
            return "end task";
        });

        new Thread(futureTask).start();
        Thread.sleep(100);
        log.info("do main");

        String result = futureTask.get();
        log.info("result：{}", result);

    }

}
