package com.quchenyang.coworkwebservice.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EchoControllerTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void echoReturnsCurrentTimeAsIsoUtcString() {
        WebTestClient webTestClient = WebTestClient.bindToApplicationContext(context).build();

        webTestClient.get()
                .uri("/rest/echo")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> {
                    assertThat(body).isNotBlank();
                    OffsetDateTime parsed = OffsetDateTime.parse(body, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    assertThat(parsed.getOffset().getTotalSeconds()).isZero();
                });
    }
}
