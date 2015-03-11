package com.gazgeek.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class HealthTest {

    @Value("${local.server.port}")
    private Integer port;

    private RestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void checkHealth() {
        getRequest("/health")
            .assertStatusCode(OK)
            .assertResponseBody("{\"status\":\"UP\"}");
    }


    private HealthResponse getRequest(String uri) {
        return new HealthResponse(restTemplate.getForEntity(getUri(uri), String.class));
    }


    protected URI getUri(String uri) {
        return UriComponentsBuilder
            .newInstance()
            .scheme("http")
            .host("localhost")
            .port(port)
            .path(uri)
            .build()
            .toUri();
    }


    private static class HealthResponse {

        private ResponseEntity<String> responseEntity;

        public HealthResponse(ResponseEntity<String> responseEntity) {
            this.responseEntity = responseEntity;
        }

        public HealthResponse assertStatusCode(HttpStatus expected) {
            assertThat(responseEntity.getStatusCode(), is(expected));
            return this;
        }

        public HealthResponse assertResponseBody(String expected) {
            assertThat(responseEntity.getBody(), is(expected));
            return this;
        }
    }
}
