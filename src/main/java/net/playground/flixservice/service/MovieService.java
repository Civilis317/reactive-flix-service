package net.playground.flixservice.service;

import net.playground.flixservice.db.MovieEvent;
import net.playground.flixservice.db.MovieRepository;
import net.playground.flixservice.model.Movie;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Flux<MovieEvent> getEvents(String movieId) {
        // fake it until we make it..
        return Flux.<MovieEvent>generate(sink -> sink.next(new MovieEvent(movieId, new Date()))).delayElements(Duration.ofSeconds(1));
    }
}
