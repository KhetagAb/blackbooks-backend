package com.tinkoff.web.blackbooks.server.discovery;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultServiceDiscoveryTest {

    private static MockWebServer mockBackEnd;

    @Autowired
    ServiceDiscovery discovery;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void itShouldPrintNotAvailable() throws Exception {
        // given
        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());

        // when
        String response = discovery.discoverService(bookhunterUrl);

        // then
        assertEquals("{ message: \"is not available\" }", response);
    }

    @Test
    public void itShouldPrintNotAvailableOtherUpService() throws Exception {
        // given
        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{ status: \"UP\" }")
                .setHeader("Content-Type", "application/json"));

        // when
        String response = discovery.discoverService(bookhunterUrl);

        // then
        assertEquals("{ message: \"is not available\" }", response);
    }

    @Test
    public void itShouldPrintDiscoveredVersion() throws Exception {
        // given
        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{ status: \"UP\" }")
                .setHeader("Content-Type", "application/json"));
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{ versions: \"xxx\" }")
                .setHeader("Content-Type", "application/json"));

        // when
        String response = discovery.discoverService(bookhunterUrl);

        // then
        assertEquals(" versions: \"xxx\" }", response);
    }

}