package com.quchenyang.coworkwebservice.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runner 服务启动入口。
 *
 * @author chenyangqu
 * @since 2026/06/23
 */
@SpringBootApplication(scanBasePackages = "com.quchenyang.coworkwebservice")
public class RunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }
}
