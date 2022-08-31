package br.com.dev.swebflux.application;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class MovieHandler {

    private final ApplicationService service;

    public MovieHandler(ApplicationService service) {
        this.service = service;
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<MovieDTO> body = request.bodyToMono(MovieDTO.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(body.flatMap(service::save), Movie.class));
    }

    public Mono<ServerResponse> getMovies(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getMovies(), Movie.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        final Mono<MovieDTO> body = request.bodyToMono(MovieDTO.class);
        final var id = Integer.parseInt(request.pathVariable("id"));
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(body.flatMap( dto -> service.update(id, dto)), Movie.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.delete(id), Void.class);
    }
 }
