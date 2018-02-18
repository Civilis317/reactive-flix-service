package net.playground.flixservice.controller;

import net.playground.flixservice.db.MovieEvent;
import net.playground.flixservice.model.Movie;
import net.playground.flixservice.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public Flux<Movie> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getEvents(String movieId) {
        return movieService.getEvents(movieId);
    }
}
