/*
This is the authentication implementation for Spring Boot 2.0.0M4

package net.playground.flixservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsRepository;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    UserDetailsRepository authentication() {
        return new MapUserDetailsRepository(User.withUsername("lzeilstra").roles("USER").password("welcome1").build(),
                User.withUsername("admin").roles("ADMIN", "USER").password("admin").build());
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(HttpSecurity http) {
        return http.httpBasic().and().authorizeExchange().anyExchange().hasRole("ADMIN").and().build();
    }

}

 */

package net.playground.flixservice.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    /*
    This is the authentication implementation for Spring Boot 2.0.0RC1
    see also: https://stackoverflow.com/questions/46999940/spring-boot-passwordencoder-error
     */
    @Bean
    MapReactiveUserDetailsService authentication() {
        return new MapReactiveUserDetailsService (User.withDefaultPasswordEncoder().username("lzeilstra").roles("USER").password("welcome1").build(),
                User.withDefaultPasswordEncoder().username("admin").roles("ADMIN", "USER").password("admin").build());
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.httpBasic().and().authorizeExchange().anyExchange().hasRole("ADMIN").and().build();
    }

}
