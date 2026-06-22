package com.quchenyang.coworkwebservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ApiApplicationTests {

    @Test
    void contextLoads() {
        assertThat(CoworkWebServiceAPIApplication.class).isNotNull();
    }
}
