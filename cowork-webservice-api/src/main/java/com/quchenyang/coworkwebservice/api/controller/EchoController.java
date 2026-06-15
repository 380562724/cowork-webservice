package com.quchenyang.coworkwebservice.api.controller;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/rest")
public class EchoController {

    @GetMapping("/echo")
    public Mono<String> echo() {
        log.info("打印一下当前时间");
        return Mono.just(OffsetDateTime.now(java.time.ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
