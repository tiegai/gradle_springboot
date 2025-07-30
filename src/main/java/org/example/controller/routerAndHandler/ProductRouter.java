package org.example.controller.routerAndHandler;

import org.example.es.model.ESInfo;
import org.example.es.repository.ESInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Optional;

@Configuration
public class ProductRouter {

    @Autowired
    private ESInfoRepo esInfoRepo;

    @Bean
    public RouterFunction<ServerResponse> productRoute(){
        return RouterFunctions.route()
                .GET("/product/{id}", request -> {
                    Optional<ESInfo> esInfo = esInfoRepo.findById(request.pathVariable("id"));
                    return esInfo.map(info -> ServerResponse.ok().body(info))
                            .orElseGet(() -> ServerResponse.notFound().build());
                })
                .onError(RuntimeException.class, (e, request) -> ServerResponse.badRequest().body(e.getMessage()))
                .build();
    }
}
