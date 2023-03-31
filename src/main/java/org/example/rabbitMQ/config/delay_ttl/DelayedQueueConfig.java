package org.example.rabbitMQ.config.delay_ttl;

import org.springframework.context.annotation.Configuration;


@Configuration
public class DelayedQueueConfig {
    // 交换机
    /*public static final String DELAYED_EXCHANGE = "delayed.exchange";
    // 队列
    public static final String DELAYED_QUEUE = "delayed.queue";
    // 路由键
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    // 声明交换机 基于插件的
    @Bean
    public CustomExchange delayedExchange(){
        Map<String, Object> args = new HashMap<>();
        // 自定义交换机的类型
        args.put("x-delayed-type", "direct");
        *//**
         * 1、交换机的名称
         * 2、交换机的类型
         * 3、是否需要持久化
         * 4、是否需要自动删除
         * 5、其他参数
         *//*
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, args);
    }

    // 声明队列
    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE);
    }

    // 绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(
            @Qualifier("delayedQueue") Queue delayedQueue,
            @Qualifier("delayedExchange")CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }*/
}
