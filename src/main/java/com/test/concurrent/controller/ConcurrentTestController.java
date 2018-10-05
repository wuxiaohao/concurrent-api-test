package com.test.concurrent.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wxh
 */
@Controller
@Slf4j
public class ConcurrentTestController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        log.info("hello world");
        return "hello world";
    }


}
