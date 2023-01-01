package com.tinkoff.web.blackbooks.server.settings;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@ConfigurationProperties(prefix = "services")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class ServicesSettings {

    private final Map<String, Service> services;

    public Service getBookHunterService() {
        return services.get("bookhunter");
    }

    public Service getBookShelfService() {
        return services.get("bookshelf");
    }

    @ConstructorBinding
    @RequiredArgsConstructor
    @Getter
    public static class Service {

        private final String host;
        private final int port;

        private final String liveliness;
        private final String version;

        public URL getVersionUrl() throws MalformedURLException {
            return getUrl(host, port, version);
        }

        public URL getLivelinessPath() throws MalformedURLException {
            return getUrl(host, port, liveliness);
        }

        private static URL getUrl(String host, int port, String path) throws MalformedURLException {
            return new URL("http", host, port, path);
        }
    }
}
