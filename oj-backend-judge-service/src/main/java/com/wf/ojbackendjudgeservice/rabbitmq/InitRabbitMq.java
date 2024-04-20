package com.wf.ojbackendjudgeservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/20 9:24
 * @Package com.wf.ojbackendjudgeservice
 * @Version 1.0
 * @Since 1.0
 */
@Slf4j
public class InitRabbitMq {

    public static void doInit(){
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.43.129");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchange";
            channel.exchangeDeclare(EXCHANGE_NAME,"direct");

            String queueName = "code_queue";
            channel.queueDeclare(queueName,true,false,false,null);
            channel.queueBind(queueName,EXCHANGE_NAME,"my_routingKey");
            log.info("消息队列启动成功");
        }catch(Exception e){
            log.error("消息队列启动失败");
        }

    }

    public static void main(String[] args) {
        doInit();
    }

}
