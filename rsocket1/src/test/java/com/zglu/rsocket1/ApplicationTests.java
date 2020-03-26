package com.zglu.rsocket1;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zglu
 */
@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {
    @Autowired
    private RSocketRequester rSocketRequester;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(rSocketRequester, "rSocketRequester must not be null!");
        Mono<Void> fnf = rSocketRequester.route("fnf").send();
        fnf.subscribe(log::info);

        Mono<String> rr = rSocketRequester
                .route("rr")
                .data("rr")
                .retrieveMono(String.class);
        rr.subscribe(log::info);

        Flux<String> rs = rSocketRequester
                .route("rs")
                .retrieveFlux(String.class);
        rs.subscribe(log::info);
    }

}
