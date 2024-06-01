package org.example.rabbitMQ.receiver.delay_ttl;


import org.springframework.stereotype.Component;


@Component
public class DelayedQueueConsumer {
    /*@RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE)
    public void receiveDelayedQueue(Message message, Channel channel){
        String msg = new String(message.getBody());
        //System.out.println("当前时间：{}, 接收到延时队列的消息为：{}", new Date(), msg);
        System.out.println("当前时间："+new Date()+", 接收到延时队列的消息为："+msg);
    }*/
}
