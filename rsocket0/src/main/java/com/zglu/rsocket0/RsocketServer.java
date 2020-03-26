package com.zglu.rsocket0;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zglu
 */
@Log4j2
@Controller
@AllArgsConstructor
public class RsocketServer {

    @MessageMapping("rr")
    public Mono<String> get(String id) {
        return Mono.just(id);
    }

    @MessageMapping("fnf")
    public Mono<Void> fnf() {
        log.info("fnf");
        return Mono.empty();
    }

    @MessageMapping("rs")
    public Flux<String> rs() {
        return Flux.just("rs");
    }

    @MessageExceptionHandler
    public Mono<String> handleException(Exception e) {
        return Mono.just(e.getMessage());
    }
}
