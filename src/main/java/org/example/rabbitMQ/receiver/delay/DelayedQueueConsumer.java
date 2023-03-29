package org.example.rabbitMQ.receiver.delay;


import com.rabbitmq.client.Channel;
import org.example.rabbitMQ.config.delay.DelayedQueueConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class DelayedQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveDelayedQueue(Message message, Channel channel){
        String msg = new String(message.getBody());
        //log.info("当前时间：{}, 接收到延时队列的消息为：{}", new Date(), msg);
        System.out.println("当前时间："+new Date()+", 接收到延时队列的消息为："+msg);
    }
}
