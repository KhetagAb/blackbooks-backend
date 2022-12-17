package com.tinkoff.web.blackbooks.server.discovery;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class DefaultServiceDiscovery implements ServiceDiscovery {

    @Override
    public String discoverService(URL url) {
        return url.toString();
    }
}
