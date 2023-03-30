package org.example.rabbitMQ.config.delay_ttl;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlQueueConfig {

    public static final String EXC_TTL = "EXC_TTL";
    public static final String QUEUE_TTL_A = "QUEUE_TTL_A";
    public static final String QUEUE_TTL_B = "QUEUE_TTL_B";
    public static final String EXC_DEAD = "EXC_DEAD";
    public static final String QUEUE_DEAD = "QUEUE_DEAD";

    // 声明 EXC_TTL 延迟交换机
    @Bean("EXC_TTL")
    public DirectExchange excTtl(){
        return new DirectExchange(EXC_TTL);
    }

    // 声明 EXC_DEAD 死信交换机
    @Bean("EXC_DEAD")
    public DirectExchange excDead(){
        return new DirectExchange(EXC_DEAD);
    }

    // 声明队列 QUEUE_TTL_A ttl 为 10s 并绑定到对应的死信交换机
    @Bean("QUEUE_TTL_A")
    public Queue queueTtlA(){
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", EXC_DEAD);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "key_dead");
        //声明队列的 TTL
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_TTL_A).withArguments(args).build();
    }

    // 声明队列 QUEUE_TTL_A 绑定 EXC_TTL 交换机
    @Bean
    public Binding queueTtlABindingExcTtl(
            @Qualifier("QUEUE_TTL_A") Queue queueTtlA,
            @Qualifier("EXC_TTL") DirectExchange excTtl) {
        return BindingBuilder.bind(queueTtlA).to(excTtl).with("key_ttl_a");
    }

    //声明队列 QUEUE_TTL_B ttl 为 40s 并绑定到对应的死信交换机
    @Bean("QUEUE_TTL_B")
    public Queue queueTtlB(){
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", EXC_DEAD);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "key_dead");
        //声明队列的 TTL
        args.put("x-message-ttl", 40000);
        return QueueBuilder.durable(QUEUE_TTL_B).withArguments(args).build();
    }

    //声明队列 QUEUE_TTL_B 绑定 EXC_TTL 交换机
    @Bean
    public Binding queueTtlBBindingExcTtl(
            @Qualifier("QUEUE_TTL_B") Queue queueTtlB,
            @Qualifier("EXC_TTL") DirectExchange excTtl){
        return BindingBuilder.bind(queueTtlB).to(excTtl).with("key_ttl_b");
    }

    //声明死信队列 QUEUE_DEAD
    @Bean("QUEUE_DEAD")
    public Queue queueD(){
        return new Queue(QUEUE_DEAD);
    }

    //声明死信队列 QUEUE_DEAD 绑定关系
    @Bean
    public Binding queueDeadBindingExcDead(
            @Qualifier("QUEUE_DEAD") Queue queueDead,
            @Qualifier("EXC_DEAD") DirectExchange excDead) {
        return BindingBuilder.bind(queueDead).to(excDead).with("key_dead");
    }


}
