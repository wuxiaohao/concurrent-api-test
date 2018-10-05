package com.test.concurrent.jucTest;

import com.test.concurrent.helper.ConcurrentTemplate;
import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * ConcurrentSkipListMap测试
 *
 * ConcurrentSkipListMap保证key的有序性，内部基于跳表实现
 *
 * @author wxh
 */
@Slf4j
public class ConcurrentSkipListMapTest extends ConcurrentTemplate {

    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
    //private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {

        ConcurrentSkipListMapTest test = new ConcurrentSkipListMapTest();
        test.doConcurrentRun();

    }

    @Override
    public void doHandle(int count) {
        map.put(count,count);
    }

    @Override
    public void doAfter() {
        for (Integer key : map.keySet()) {
            log.info("key={},value={}",key,map.get(key));
        }
        log.info("size={}",map.size());
    }
}
