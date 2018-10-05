package com.test.concurrent.atomicTest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟 count++
 *
 * 测试结果：
 *  count++ 不保证原子性和可见性，
 *  volatile 可以保证可见性，但是不能保证原子性
 * @author wxh
 */
@Slf4j
public class ConcurrentTest extends ConcurrentTemplate {

//    public int count = 0;
    public volatile int count = 0;

    @Override
    public void doHandle(int num) {
        count++;
    }

    @Override
    public void doAfter() {
        log.info("count = {}", count);
    }

    public static void main(String[] args) {

        ConcurrentTest concurrentTest = new ConcurrentTest();
        try {
            concurrentTest.doConcurrentRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
