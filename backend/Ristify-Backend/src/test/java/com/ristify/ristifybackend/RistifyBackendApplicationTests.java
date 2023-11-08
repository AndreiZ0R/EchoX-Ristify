package com.ristify.ristifybackend;

import Models.Playlists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;

@SpringBootTest
class RistifyBackendApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true, equalTo(true));
        Playlists p = mock(Playlists.class);
    }

}
