package com.test.concurrent.atomicTest;

import com.test.concurrent.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater测试
 *
 * AtomicIntegerFieldUpdater 可以保证对象的某一个field的原子性
 *
 * 测试结果：
 *  注意AtomicIntegerFieldUpdater要求对象的field 必须是非static、 需要声明为 public volatile 如：
 *  public volatile int age;
 *
 * @author wxh
 */
@Slf4j
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<UserDto> updater = AtomicIntegerFieldUpdater.newUpdater(UserDto.class, "age");

    public static void main(String[] args) {

        UserDto userDto = new UserDto();
        userDto.setAge(100);

        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();

        if (updater.compareAndSet(userDto, 100, 101)) {
            log.info("更新成功，age={}", userDto.getAge());
        }

        if (updater.compareAndSet(userDto, 100, 101)) {
            log.info("更新成功，age={}", userDto.getAge());
        } else {
            log.info("更新失败，当前值：{}",userDto.getAge());
        }

    }


}
