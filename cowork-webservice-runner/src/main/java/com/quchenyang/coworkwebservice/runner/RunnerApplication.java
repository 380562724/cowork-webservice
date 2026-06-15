package com.quchenyang.coworkwebservice.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.quchenyang.coworkwebservice")
public class RunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }
}
