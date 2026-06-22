package com.quchenyang.coworkwebservice.component;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentSmokeTest {

    @Test
    void componentPackageIsAccessible() {
        Package pkg = ComponentSmokeTest.class.getPackage();
        assertThat(pkg).isNotNull();
        assertThat(pkg.getName()).isEqualTo("com.quchenyang.coworkwebservice.component");
    }
}
