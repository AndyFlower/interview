package com.slp.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author sanglp
 * @create 2018-12-19 13:56
 * @desc Event  用户注册事件
 **/
public class UserRegisterEvent extends ApplicationEvent {

    /**
     *
     * @param name 就是source
     */
    public UserRegisterEvent(String name) {
        super(name);
    }
}
