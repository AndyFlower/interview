package com.slp.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author sanglp
 * @create 2018-12-19 14:08
 * @desc 邮件服务
 **/
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("邮件服务收到通知，给"+userRegisterEvent.getSource());
    }
}
