package net.playground.flixservice.db;

import net.playground.flixservice.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class SampleDataInitializer implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(SampleDataInitializer.class.getName());

    private final MovieRepository movieRepository;

    public SampleDataInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux<Movie> movieFlux =  Flux.just("Silence of the Lambdas", "Winnie the Pooh", "Back to the Future", "AEon Flux")
                .map( s -> new Movie(null, s))
                .flatMap(movieRepository::save);

        movieRepository.deleteAll()
                .thenMany(movieFlux)
                .thenMany(movieRepository.findAll())
                .subscribe(m -> logger.info(m.toString()));

    }
}
