package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConcreteMessageControllerTest {

    private ConcreteMessageController sut;

    @BeforeEach
    void beforeAbstractMessageControllerUnitTest() {
        sut = new ConcreteMessageController();
    }

    @Test
    void successResponse_withData_hasCorrectOutput() {
        // Given
        String message = Randoms.alphabetic();
        Object body = Randoms.randomStringList();
        HttpHeaders headers = new HttpHeaders();
        headers.set(Randoms.alphabetic(), Randoms.alphabetic());

        // When
        Response response = sut.response(body, headers, HttpStatus.OK, message);

        // Then
        assertThat(response.getBody(), equalTo(TestUtils.createResponseBody(body, HttpStatus.OK, message)));
        assertThat(response.getHeaders(), equalTo(TestUtils.createResponseHeaders(headers)));
    }
}