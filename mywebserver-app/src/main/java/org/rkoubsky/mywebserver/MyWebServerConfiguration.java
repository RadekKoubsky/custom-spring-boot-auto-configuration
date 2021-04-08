package org.rkoubsky.mywebserver;

import org.rkoubsky.mywebserver.autoconfigure.MyWebServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyWebServerConfiguration {

    public static final boolean HTTP_2_ENABLED = true;
    private final MyWebServerProperties properties;

    public MyWebServerConfiguration(MyWebServerProperties properties) {
        this.properties = properties;
    }

    /**
     * Delete this user configuration to enable auto configuration at
     * {@link org.rkoubsky.mywebserver.autoconfigure.MyWebServerAutoConfiguration}
     * */
    @Bean
    public MyWebServer myWebServer(){
        return new MyWebServer(this.properties.getHost(),
                               this.properties.getPort(),
                               this.properties.isSslEnabled(),
                               HTTP_2_ENABLED);
    }
}
