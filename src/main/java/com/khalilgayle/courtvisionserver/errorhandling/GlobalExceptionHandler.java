package com.khalilgayle.courtvisionserver.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseStatusExceptionHandler {
    @ExceptionHandler(PlayerNotFoundException.class)
    public Mono<ResponseEntity<String>> handlePlayerNotFoundException(PlayerNotFoundException ex, ServerWebExchange serverWebExchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler(PlayersNotFoundException.class)
    public Mono<ResponseEntity<String>> handlePlayersNotFoundException(PlayersNotFoundException ex, ServerWebExchange serverWebExchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler(TeamsNotFoundException.class)
    public Mono<ResponseEntity<String>> handleTeamsNotFoundException(TeamsNotFoundException ex, ServerWebExchange serverWebExchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidQueryParametersException.class)
    public Mono<ResponseEntity<String>> handleInvalidQueryParametersException(InvalidQueryParametersException ex, ServerWebExchange serverWebExchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleGenericException(Exception ex, ServerWebExchange serverWebExchange) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage()));
    }
}
