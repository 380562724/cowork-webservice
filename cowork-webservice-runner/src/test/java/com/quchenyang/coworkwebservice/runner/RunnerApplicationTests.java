package com.quchenyang.coworkwebservice.runner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RunnerApplicationTests {

    @Test
    void contextLoads() {
        assertThat(RunnerApplication.class).isNotNull();
    }
}
