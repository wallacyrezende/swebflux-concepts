package br.com.dev.swebflux.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface ApplicationService {
    Flux<Movie> getMovies();
    Mono<Movie> save(MovieDTO dto);
    Mono<Void> delete(Integer id);
    Mono<Movie> update(Integer id, MovieDTO dto);
    Flux<Tuple2<Long, Movie>> getAllEvents();
}
