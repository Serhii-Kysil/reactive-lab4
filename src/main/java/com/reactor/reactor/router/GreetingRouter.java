package com.reactor.reactor.router;

import com.reactor.reactor.handler.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RequestPredicate accept = accept(MediaType.APPLICATION_JSON);
        return RouterFunctions
                .route(RequestPredicates.GET("/"), greetingHandler::hello)
                .andRoute(RequestPredicates.GET("/users").and(accept), greetingHandler::users)
                .andRoute(RequestPredicates.GET("/admin"), greetingHandler::admin)
                .andRoute(RequestPredicates.GET("/logout"), greetingHandler::logout);

    }
}

