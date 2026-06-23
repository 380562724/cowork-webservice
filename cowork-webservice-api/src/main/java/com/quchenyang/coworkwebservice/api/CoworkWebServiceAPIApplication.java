package com.quchenyang.coworkwebservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API 服务启动入口。
 *
 * @author chenyangqu
 * @since 2026/06/23
 */
@SpringBootApplication(scanBasePackages = "com.quchenyang.coworkwebservice")
public class CoworkWebServiceAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoworkWebServiceAPIApplication.class, args);
    }
}
