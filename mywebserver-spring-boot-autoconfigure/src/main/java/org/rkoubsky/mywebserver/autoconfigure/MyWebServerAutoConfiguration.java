package org.rkoubsky.mywebserver.autoconfigure;

import org.rkoubsky.mywebserver.MyWebServer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(MyWebServer.class)
@EnableConfigurationProperties(MyWebServerProperties.class)
public class MyWebServerAutoConfiguration {

    private final MyWebServerProperties properties;

    public MyWebServerAutoConfiguration(MyWebServerProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(MyWebServer.class)
    public MyWebServer myWebServer(){
        return new MyWebServer(this.properties.getHost(),
                               this.properties.getPort(),
                               this.properties.isSslEnabled());
    }
}
