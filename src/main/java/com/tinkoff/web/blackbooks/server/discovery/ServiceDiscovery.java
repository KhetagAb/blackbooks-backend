package com.tinkoff.web.blackbooks.server.discovery;

import java.net.URI;

public interface ServiceDiscovery {

    String discoverService(URI uri);
}
