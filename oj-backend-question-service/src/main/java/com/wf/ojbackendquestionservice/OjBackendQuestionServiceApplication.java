package com.wf.ojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication   //exclude = {RedisAutoConfiguration.class}
@MapperScan(value = "com.wf.ojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.wf")
public class OjBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjBackendQuestionServiceApplication.class, args);
    }

}
