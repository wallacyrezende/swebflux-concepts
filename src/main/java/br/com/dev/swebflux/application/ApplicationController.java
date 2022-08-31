package br.com.dev.swebflux.application;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @Operation( summary = "Save a movie" )
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> save(@RequestBody MovieDTO dto) {
        return service.save(dto);
    }

    @Operation( summary = "Get movies" )
    @GetMapping("/movies")
    public Flux<Movie> get() {
        return service.getMovies();
    }

    @Operation( summary = "Update the information of the a movie" )
    @PatchMapping("/update/{id}")
    public Mono<Movie> update(@RequestBody MovieDTO dto, @PathVariable Integer id) {
        return service.update(id, dto);
    }

    @Operation( summary = "Delete a movie by id" )
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
