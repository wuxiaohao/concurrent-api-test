package com.test.concurrent.jucTest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * CopyOnWriteArrayList测试
 *
 * add、set、remove，都会拷贝原数组，修改后替换原来的数组，实现另类的线程安全
 * 读不加锁，但是在读的适合，有线程在update，还是会读到旧值
 *
 * @author wxh
 */
@Slf4j
public class CopyOnWriteArrayListTest extends ConcurrentTemplate {

    //private static List<Integer> list = new ArrayList<>();
    private static List<Integer> list = new CopyOnWriteArrayList<>();


    @Override
    public void doHandle() {

        list.add(1);

    }

    @Override
    public void doAfter() {
        log.info("size={}",list.size());
    }

    public static void main(String[] args) throws Exception {

        CopyOnWriteArrayListTest test = new CopyOnWriteArrayListTest();
        test.doConcurrentRun(5000,100);

    }

}
