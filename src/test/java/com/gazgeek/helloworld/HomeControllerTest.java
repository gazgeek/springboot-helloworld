package com.gazgeek.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class HomeControllerTest {

    @Value("${local.server.port}")
    private Integer port;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void helloWorld() {
        getRequest("/")
            .assertStatusCode(OK)
            .assertResponseBody("Hello from GazGeek!");
    }

    private HelloWorldResponse getRequest(String uri) {
        return new HelloWorldResponse(restTemplate.getForEntity(getUri(uri), String.class));
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

}
