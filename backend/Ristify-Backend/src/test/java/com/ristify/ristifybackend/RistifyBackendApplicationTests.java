package com.ristify.ristifybackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class RistifyBackendApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true, equalTo(true));
    }
}
