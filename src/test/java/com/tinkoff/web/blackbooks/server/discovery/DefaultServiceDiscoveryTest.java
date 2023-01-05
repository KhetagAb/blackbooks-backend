package com.tinkoff.web.blackbooks.server.discovery;

@Deprecated
class DefaultServiceDiscoveryTest { // toDo integration testing

//    ServicesSettings settings = mock(ServicesSettings.class);
//
//
//
//    @Autowired
//    ServiceDiscovery discovery;
//
//    @BeforeAll
//    static void setUp() throws IOException {
//        mockBackEnd = new MockWebServer();
//        mockBackEnd.start();
//    }
//
//    @AfterAll
//    static void tearDown() throws IOException {
//        mockBackEnd.shutdown();
//    }
//
//    @Test
//    public void itShouldPrintNotAvailable() throws Exception {
//        // given
//        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());
//
//        // when
//        IllegalStateException e = assertThrows(
//                IllegalStateException.class,
//                () -> discovery.discoverService(bookhunterUrl)
//                        .block(RESPONSE_TIMEOUT)
//        );
//
//        // then
//        assertTrue(e.getMessage().contains("Timeout"));
//    }
//
//    @Test
//    public void itShouldPrintNotAvailableOtherUpService() throws Exception {
//        // given
//        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());
//        when(service.discoverAll())
//                .thenReturn(Flux.just("{ status: \"UP\" }"));
////        mockBackEnd.enqueue(new MockResponse()
////                .setResponseCode(200)
////                .setBody("{ status: \"UP\" }")
////                .setHeader("Content-Type", "application/json"));
//
//        // when
//        String response = discovery.discoverService(bookhunterUrl)
//                .block(RESPONSE_TIMEOUT);
//
//        // then
//        assertEquals("{ message: \"is not available\" }", response);
//    }
//
//    @Test
//    public void itShouldPrintDiscoveredVersion() throws Exception {
//        // given
//        URL bookhunterUrl = new URL("http://localhost:" + mockBackEnd.getPort());
//        mockBackEnd.enqueue(new MockResponse()
//                .setBody("{ status: \"UP\" }")
//                .setHeader("Content-Type", "application/json"));
//        mockBackEnd.enqueue(new MockResponse()
//                .setBody("{ versions: \"xxx\" }")
//                .setHeader("Content-Type", "application/json"));
//
//        // when
//        String response = discovery.discoverService(bookhunterUrl).block(RESPONSE_TIMEOUT);
//
//        // then
//        assertEquals(" versions: \"xxx\" }", response);
//    }

}