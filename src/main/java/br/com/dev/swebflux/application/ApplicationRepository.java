package br.com.dev.swebflux.application;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApplicationRepository extends ReactiveCrudRepository<Movie, Integer> {
}
