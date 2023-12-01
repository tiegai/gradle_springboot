package org.example;

/*import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitAdmin;*/
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("org.example.sharding.mapper")
@SpringBootApplication
@EnableAsync
public class MainApp {
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