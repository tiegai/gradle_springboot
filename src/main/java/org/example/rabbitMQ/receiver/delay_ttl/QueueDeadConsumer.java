package org.example.rabbitMQ.receiver.delay_ttl;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitMQ.config.delay_ttl.TtlQueueConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class QueueDeadConsumer {

    @RabbitListener(queues = TtlQueueConfig.QUEUE_DEAD)
    public void receiveQueueDead(Message message, Channel channel){
        String msg = new String(message.getBody());
        //System.out.println("当前时间：{}, 接收到的消息为：{}", new Date(), msg);
        System.out.println("当前时间："+new Date()+", 接收到的消息为："+msg);
    }
}

