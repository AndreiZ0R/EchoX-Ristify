package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.AppHttpMethod;
import com.ristify.ristifybackend.utils.AppUtils;
import com.ristify.ristifybackend.utils.Randoms;
import com.ristify.ristifybackend.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AbstractMessageControllerUnitTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMessageControllerUnitTest.class);

    private AbstractMessageController sut;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void beforeAbstractMessageControllerUnitTest() {
        sut = new AbstractMessageController();
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

    /**
     * <b>Asserts that a given method on a given route returns a success response with the given payload.</b>
     * Utility function for rest controllers.
     *
     * @param method   <i>String</i>: HTTP Method to test: "GET", "POST", "PUT", "DELETE"...
     * @param endPoint <i>String</i>: Endpoint to test. Eg: <i>/api/test</i>
     * @param body     <i>Object</i>: Response body. Eg: <i>new User("Andrei",25)</i>
     */
    void assertThatRespondedWithSuccess(final AppHttpMethod method, final String endPoint, final Object body) {
        try {
            mockMvc.perform(getHttpMethod(method, TestUtils.buildTestUrl(endPoint, port)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(
                            content().string(containsString(TestUtils.buildJsonString(body, AppUtils.SUCCESS))));
        } catch (Exception e) {
            LOG.error(e::getMessage);
        }
    }

    /**
     * <b>Asserts that a given method on a given route returns a failure response with the given error message and status code.</b>
     * Utility function for rest controllers.
     *
     * @param method       <i>String</i>: HTTP Method to test: "GET", "POST", "PUT", "DELETE"...
     * @param endPoint     <i>String</i>: Endpoint to test. Eg: <i>/api/test</i>
     * @param status       <i>HttpStatus</i>: The status code for the response.
     * @param errorMessage <i>String</i>: The correlated error message.
     */
    void assertThatRespondedWithFailure(final AppHttpMethod method, final String endPoint, final HttpStatus status, final String errorMessage) {
        try {
            mockMvc.perform(getHttpMethod(method, TestUtils.buildTestUrl(endPoint, port)))
                    .andDo(print())
                    .andExpect(status().is(status.value()))
                    .andExpect(
                            content().string(containsString(TestUtils.buildJsonString(errorMessage))));
        } catch (Exception e) {
            LOG.error(e::getMessage);
        }
    }

    private MockHttpServletRequestBuilder getHttpMethod(final AppHttpMethod method, final String URL) {
        return switch (method) {
            case GET -> get(URL);
            case PUT -> put(URL);
            case POST -> post(URL);
            case DELETE -> delete(URL);
        };
    }
}