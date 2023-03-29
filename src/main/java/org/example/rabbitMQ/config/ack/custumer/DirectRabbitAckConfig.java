package org.example.rabbitMQ.config.ack.custumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitAckConfig {

    @Bean
    public Queue TestAckDirectQueue1() {
        return new Queue("TestDirectAckQueue1",true);
    }

    @Bean
    public Queue TestAckDirectQueue2() {
        return new Queue("TestDirectAckQueue2",true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectAckExchange1() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectAckExchange1", true, false);
    }

    @Bean
    DirectExchange TestDirectAckExchange2() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectAckExchange2", true, false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingAckDirect1() {
        return BindingBuilder.bind(TestAckDirectQueue1()).to(TestDirectAckExchange1()).with("TestDirectAckRouting1");
    }

    @Bean
    Binding bindingAckDirect2() {
        return BindingBuilder.bind(TestAckDirectQueue2()).to(TestDirectAckExchange2()).with("TestDirectAckRouting2");
    }

}
