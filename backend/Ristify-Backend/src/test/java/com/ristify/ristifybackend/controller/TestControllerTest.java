package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.utils.AbstractControllerUnitTest;
import com.ristify.ristifybackend.utils.AppHttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestControllerTest extends AbstractControllerUnitTest {

    @BeforeEach
    void beforeTestControllerTest() {
        super.setUp();
    }

    @Test
    void testController_sendOkRequest_returnOkResponse() {
        assertThatRespondedWithSuccess(AppHttpMethod.GET, "/api/test/success", "ok");
    }
}
