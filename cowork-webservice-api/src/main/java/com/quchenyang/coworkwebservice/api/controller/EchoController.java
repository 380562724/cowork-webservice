package com.quchenyang.coworkwebservice.api.controller;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 回显接口，提供普通与定时推送两种模式。
 *
 * @author chenyangqu
 * @since 2026/06/23
 */
@RestController
@Slf4j
@RequestMapping("/rest")
public class EchoController {

    @GetMapping("/echo")
    public Mono<String> echo() {
        log.info("打印一下当前时间");
        return Mono.just(OffsetDateTime.now()
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    @GetMapping("/echo2")
    public Flux<String> echo2() {
        log.info("开始每秒推送当前时间");
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> String.format("第%d次: %s", tick + 1,
                        OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
    }
}
