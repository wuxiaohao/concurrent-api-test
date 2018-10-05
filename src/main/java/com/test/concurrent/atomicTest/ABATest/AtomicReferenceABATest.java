package com.test.concurrent.atomicTest.ABATest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 模拟CAS潜在的ABA问题，并使用java标准类库提供的AtomicStampedReference解决ABA问题
 * @author wxh
 */
@Slf4j
public class AtomicReferenceABATest {

    public static final AtomicStampedReference<String> VALUE = new AtomicStampedReference<String>("A",0);

    public static final CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            int stamp = VALUE.getStamp();
            if (VALUE.compareAndSet("A","B", stamp, stamp + 1)) {
                log.info("线程{},将VALUE改为:{},此时版本号为:{}",Thread.currentThread(), VALUE.getReference(),stamp);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if ("B".equals(VALUE.getReference())) {
                if (stamp == VALUE.getStamp()) {
                    log.info("线程{},再次取到value = {},没发生ABA问题",Thread.currentThread());
                } else {
                    log.info("线程{},再次取到value = {},发生了ABA问题，最初版本号={},此时版本号={}"
                            ,Thread.currentThread() ,VALUE.getReference() ,stamp ,VALUE.getStamp());
                }
            }

            countDownLatch.countDown();

        });

        Thread t2 = new Thread(() -> {

            int stamp = VALUE.getStamp();
            if (VALUE.compareAndSet("B", "C", stamp , stamp + 1)) {
                log.info("线程{},把B改成C",Thread.currentThread());
            }

            log.info("线程{},做了其他事",Thread.currentThread());

            stamp = VALUE.getStamp();
            if (VALUE.compareAndSet("C", "B", stamp , stamp + 1)) {
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
