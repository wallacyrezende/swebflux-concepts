package br.com.dev.swebflux.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class MovieRouter {

    @Bean
    public RouterFunction<ServerResponse> route(MovieHandler handler) {
        return RouterFunctions
                .route(GET("/movies").and(accept(MediaType.APPLICATION_JSON)), handler::getMovies)
                .andRoute(POST("/save").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(PATCH("/update/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}
