package org.example;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

    // test git
    // rebase test1
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);
    }

    /**
     * 上报application 到 Prometheus
     */
    /*@Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "gradle_springboot");
    }*/
}