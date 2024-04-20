package com.wf.ojbackendjudgeservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.wf.model.entity.QuestionSubmit;
import com.wf.ojbackendjudgeservice.JudgeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author 玉米排骨汤
 * @Date 2024/4/20 9:43
 * @Package com.wf.ojbackendjudgeservice.rabbitmq
 * @Version 1.0
 * @Since 1.0
 */
@Component
@Slf4j
public class MyMessageConsumer {

    @Resource
    private JudgeService judgeService;

    @SneakyThrows
    @RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag){
        log.info("receiveMessage message = {}",message);
        long questionSubmitId = Long.parseLong(message);
        try{
            judgeService.doJudge(questionSubmitId);
            channel.basicAck(deliveryTag,false);
        }catch(Exception e){
            channel.basicNack(deliveryTag,false,false);
        }
    }

}
