package net.playground.flixservice.db;

import net.playground.flixservice.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

    Flux<Movie> findByTitle(String title);
}
