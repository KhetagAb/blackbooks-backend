package com.tinkoff.web.blackbooks.server.discovery;

import com.tinkoff.web.blackbooks.server.service.DiscoveryService;
import com.tinkoff.web.blackbooks.server.settings.ServicesSettings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class DiscoveryServiceTest {

    ServiceDiscovery serviceDiscovery = mock(ServiceDiscovery.class);

    ServicesSettings settings = mock(ServicesSettings.class);

    @InjectMocks
    DiscoveryService discovery;

    @Test
    public void itShouldPrintNotAvailable() throws Exception {
        // given
        var service = ServicesSettings.Service.of("host", 0);
        when(settings.getServices())
                .thenReturn(Map.of("service", service));
        when(serviceDiscovery.discoverService(service.getUrl()))
                .thenReturn(Mono.just("{ \"message:\" \"error\" }"));

        // when
        String collect = discovery.discoverAll()
                .toStream()
                .collect(Collectors.joining());

        // then
        assertEquals("\"service\": { \"message:\" \"error\" }", collect);
    }

}