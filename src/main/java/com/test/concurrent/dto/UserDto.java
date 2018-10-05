package com.test.concurrent.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wxh
 */
public class UserDto {

    /**
     * 用户id
     */
    @Getter
    @Setter
    private int id ;

    /**
     * 用户名
     */
    @Getter
    @Setter
    private String name;

    /**
     * 用户年龄
     */
    @Getter
    @Setter
    public volatile int age;

}
