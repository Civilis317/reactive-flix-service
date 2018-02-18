package net.playground.flixservice.controller;

import net.playground.flixservice.db.MovieEvent;
import net.playground.flixservice.model.Movie;
import net.playground.flixservice.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MovieHandler {
    private final MovieService movieService;

    public MovieHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    public Mono<ServerResponse> all(ServerRequest serverRequest) {
        return ServerResponse.ok().body(movieService.getAllMovies(), Movie.class);
    }

    public Mono<ServerResponse> byId(ServerRequest serverRequest) {
        return ServerResponse.ok().body(movieService.getMovieById(serverRequest.pathVariable("id")), Movie.class);
    }

    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(movieService.getEvents(serverRequest.pathVariable("id")), MovieEvent.class);
    }

}
