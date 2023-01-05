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

    private final String liveliness;
    private final String version;

    private final Map<String, Service> services;

    public Service getBookHunterService() {
        return services.get("bookhunter");
    }

    public Service getBookShelfService() {
        return services.get("bookshelf");
    }

    @ConstructorBinding
    @Getter
    public static class Service {

        private final URL url;

        Service(String host, int port) throws MalformedURLException {
            this.url = new URL("http://" + host + ":" + port);
        }

        public static Service of(String host, int port) throws MalformedURLException {
            return new Service(host, port);
        }
    }
}
