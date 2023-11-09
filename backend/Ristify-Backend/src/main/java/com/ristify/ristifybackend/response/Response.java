package com.ristify.ristifybackend.response;


import com.ristify.ristifybackend.utils.AppUtils;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Response extends ResponseEntity<Object> implements Serializable {
    private final Map<String, Object> body;
    private final String message;
    private final HttpStatus status;
    private final HttpHeaders headers;

    public Response(final Builder builder) {
        super(builder.body, builder.headers, builder.status);

        this.message = builder.message;
        this.status = builder.status;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response response = (Response) o;
        return Objects.equals(message, response.message) && status == response.status && Objects.equals(body, response.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status, body);
    }

    @Override
    public String toString() {
        return "Response{" + "message='" + message + '\'' +
               ", status=" + status +
               ", body=" + body +
               '}';
    }

    public static class Builder {
        private String message;
        private HttpStatus status;
        private final Map<String, Object> body = new HashMap<>();
        private final HttpHeaders headers = new HttpHeaders();

        public Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public Builder withStatus(final HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder withField(final String fieldName, final Object field) {
            body.put(fieldName, field);
            return this;
        }

        public Builder withMultipleFields(final Map<String, Object> fields) {
            body.putAll(fields);
            return this;
        }

        public Builder withBody(final Object body) {
            this.body.put(AppUtils.PAYLOAD, body);
            return this;
        }

        public Builder withHeader(final String headerName, final String headerValue) {
            headers.set(headerName, headerValue);
            return this;
        }

        public Builder withHeaders(final HttpHeaders headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Response build() {
            body.put(AppUtils.MESSAGE, message);
            body.put(AppUtils.STATUS, status);
            headers.set(HttpHeaders.CONTENT_TYPE, AppUtils.APPLICATION_JSON);

            return new Response(this);
        }
    }
}
