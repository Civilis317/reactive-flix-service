package net.playground.flixservice;

import net.playground.flixservice.controller.MovieHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@ComponentScan({"net.playground.flixservice.db"
        , "net.playground.flixservice.service",
        "net.playground.flixservice.controller"
})
public class FlixServiceApplication {

    @Bean
    RouterFunction<ServerResponse> routerFunction(MovieHandler movieHandler) {
        return route(GET("/movies"), movieHandler::all)
                .andRoute(GET("/movies/{id}"), movieHandler::byId)
                .andRoute(GET("/movies/{id}/events"), movieHandler::events);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlixServiceApplication.class, args);
    }
}
