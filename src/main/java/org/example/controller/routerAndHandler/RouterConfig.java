package org.example.controller.routerAndHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> helloRoute(){
        return RouterFunctions.route()
                .GET("/hello", request -> ServerResponse.ok().body("Hello World!"))
                .build();
    }
}
