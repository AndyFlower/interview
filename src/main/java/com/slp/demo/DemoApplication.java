package com.slp.demo;

import com.slp.demo.event.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    UserService userService;
    @RequestMapping("/register")
    public String register(){
        userService.register("slp");
        return "success";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}

