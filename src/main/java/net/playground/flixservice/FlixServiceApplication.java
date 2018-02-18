package net.playground.flixservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"net.playground.flixservice.db"
        , "net.playground.flixservice.service",
        "net.playground.flixservice.controller"
})
public class FlixServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlixServiceApplication.class, args);
    }
}
