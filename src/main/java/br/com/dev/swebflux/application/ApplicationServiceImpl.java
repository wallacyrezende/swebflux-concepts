package br.com.dev.swebflux.application;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository repository;

    public ApplicationServiceImpl(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Movie> getMovies() {
        return repository.findAll();
    }

    @Override
    public Mono<Movie> save(MovieDTO dto) {
        Movie movie = Movie.builder()
                .name(dto.getName())
                .build();
        return repository.save(movie);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Movie> update(Integer id, MovieDTO dto) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setName(dto.getName());
                    return movie;
                })
                .flatMap(movie -> repository.save(movie));
    }
}
