package com.slp.demo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author sanglp
 * @create 2018-12-19 13:59
 * @desc 用户注册服务 事件发布者
 **/
@Service
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    public  void register(String name){
        System.out.println("用户："+name+"已注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent((name)));
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
