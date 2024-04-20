package com.wf.ojbackendquestionservice.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/20 9:39
 * @Package com.wf.ojbackendquestionservice.rabbitmq
 * @Version 1.0
 * @Since 1.0
 */
@Component
public class MyMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey, String message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

}
