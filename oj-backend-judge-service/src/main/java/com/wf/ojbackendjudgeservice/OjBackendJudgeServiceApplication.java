package com.wf.ojbackendjudgeservice;

import com.wf.ojbackendjudgeservice.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication   //exclude = {RedisAutoConfiguration.class}
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wf.ojbackendserviceclient"})
//@MapperScan(value = "com.wf.ojbackendjudgeservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.wf")
public class OjBackendJudgeServiceApplication {

    public static void main(String[] args) {
        //初始化消息队列
        InitRabbitMq.doInit();
        SpringApplication.run(OjBackendJudgeServiceApplication.class, args);
    }

}
