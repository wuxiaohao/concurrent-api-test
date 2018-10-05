package com.test.concurrent.atomicTest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * AtomicBoolean test
 * @author wxh
 */
@Slf4j
public class AtomicBooleanTest extends ConcurrentTemplate {

    private boolean isOK = false;

    private AtomicBoolean isTrue = new AtomicBoolean(false);

    public static void main(String[] args) {

        AtomicBooleanTest test = new AtomicBooleanTest();
        try {
            test.doConcurrentRun(1000,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doHandle() {
        if (isTrue.compareAndSet(false, true)) {
            log.info("isTrue = {}", isTrue.get());
        }
//        if (!isOK) {
//            isOK = true;
//            log.info("isOK = {}", isOK);
//        }
    }

    @Override
    public void doAfter() {

    }
}
