package com.quchenyang.coworkwebservice.component;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentSmokeTest {

    @Test
    void componentSourceIsLoadable() throws ClassNotFoundException {
        Class<?> klass = Class.forName("com.quchenyang.coworkwebservice.component.package-info");
        assertThat(klass).isNotNull();
        assertThat(klass.getPackage().getName()).isEqualTo("com.quchenyang.coworkwebservice.component");
    }
}
