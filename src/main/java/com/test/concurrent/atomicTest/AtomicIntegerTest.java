package com.test.concurrent.atomicTest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger原子性操作测试
 * @author wxh
 */
@Slf4j
public class AtomicIntegerTest extends ConcurrentTemplate  {

    AtomicInteger num = new AtomicInteger(0);

    @Override
    public void doHandle(int count) {
        num.incrementAndGet();
    }

    @Override
    public void doAfter() {
        log.info("count = {}", num.get());
    }

    public static void main(String[] args) {

        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        try {
            atomicIntegerTest.doConcurrentRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
