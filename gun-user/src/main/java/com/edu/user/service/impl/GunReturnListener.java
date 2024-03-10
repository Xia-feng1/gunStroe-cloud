package com.edu.user.service.impl;

import com.edu.common.domain.LendMSG;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GunReturnListener {
    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(name = "delay-queue",durable = "true"),
            exchange = @Exchange(value = "delay-exchange",type = ExchangeTypes.TOPIC,delayed = "true"),
            key="delayed"))
    public void receive(  LendMSG<Object> reminder){
        System.out.println("GunReturnListener消费者消费的消息: " +reminder.getData());
    }
}
