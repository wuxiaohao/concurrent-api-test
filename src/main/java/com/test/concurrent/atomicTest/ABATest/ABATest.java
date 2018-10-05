package com.test.concurrent.atomicTest.ABATest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 模拟CAS潜在的ABA问题，重现ABA场景
 * @author wxh
 */
@Slf4j
public class ABATest {

    public static final AtomicReference<String> VALUE = new AtomicReference<String>("A");

    public static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            if (VALUE.compareAndSet("A","B")) {
                log.info("线程{},将VALUE改为:{}",Thread.currentThread(), VALUE.get());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("线程{},再次取到value = {}",Thread.currentThread(), VALUE.get());

            countDownLatch.countDown();

        });

        Thread t2 = new Thread(() -> {


            if (VALUE.compareAndSet("B", "C")) {
                log.info("线程{},把B改成C",Thread.currentThread());
            }

            log.info("线程{},做了其他事",Thread.currentThread());

            if (VALUE.compareAndSet("C", "B")) {
                log.info("线程{},把C改成B",Thread.currentThread());
            }

            countDownLatch.countDown();

        });

        t1.start();
        t1.join(100);
        t2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
