package com.tinkoff.web.blackbooks.server.settings;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.net.MalformedURLException;
import java.net.URL;

@ConfigurationProperties(prefix = "services")
@ConstructorBinding
@RequiredArgsConstructor
public class ServicesSettings {

    private final String discoveryPath;

    private final String bookHunterHost;
    private final int bookHunterPort;

    private final String bookShelfHost;
    private final int bookShelfPort;

    public URL getBookHunterDiscoveryUrl() throws MalformedURLException {
        return getUrl(bookHunterHost, bookHunterPort);
    }

    public URL getBookShelfDiscoveryUrl() throws MalformedURLException {
        return getUrl(bookShelfHost, bookShelfPort);
    }

    private URL getUrl(String bookShelfHost, int bookShelfPort) throws MalformedURLException {
        return new URL("http", bookShelfHost, bookShelfPort, discoveryPath);
    }
}
