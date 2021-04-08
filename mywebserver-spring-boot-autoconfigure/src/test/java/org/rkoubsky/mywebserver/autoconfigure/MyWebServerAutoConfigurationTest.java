package org.rkoubsky.mywebserver.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.rkoubsky.mywebserver.MyWebServer;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class MyWebServerAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MyWebServerAutoConfiguration.class));


    @Test
    public void whenNoUserConfigurationProvided_thenAutoConfigurationIsApplied() throws Exception {
        this.contextRunner.withPropertyValues("mywebserver.port=449", "mywebserver.sslEnabled=true").run((context) -> {
            assertThat(context).hasSingleBean(MyWebServer.class);
            MyWebServer myWebServer = context.getBean(MyWebServer.class);
            myWebServer.start();
            assertThat(myWebServer.getHost()).isEqualTo("localhost");
            assertThat(myWebServer.getPort()).isEqualTo(449);
            assertThat(myWebServer.isSslEnabled()).isTrue();
            assertThat(myWebServer.isHttp2Enabled()).isFalse();
        });
    }

    @Test
    public void whenUserConfigurationProvided_thenAutoConfigurationIsDisabled() throws Exception {
        this.contextRunner.withUserConfiguration(MyWebServerUserConfiguration.class)
                          .withPropertyValues("mywebserver.port=449", "mywebserver.sslEnabled=true").run((context) -> {
            assertThat(context).hasSingleBean(MyWebServer.class);
            MyWebServer myWebServer = context.getBean(MyWebServer.class);
            myWebServer.start();

            assertThat(context).getBean("myUserDefinedWebServer").isSameAs(context.getBean(MyWebServer.class));
            assertThat(myWebServer.getHost()).isEqualTo("my.example.com");
            assertThat(myWebServer.getPort()).isEqualTo(8878);
            assertThat(myWebServer.isSslEnabled()).isTrue();
            assertThat(myWebServer.isHttp2Enabled()).isTrue();
        });
    }

    @Configuration
    static class MyWebServerUserConfiguration {

        @Bean
        public MyWebServer myUserDefinedWebServer(){
            return new MyWebServer("my.example.com",
                                   8878,
                                   true,
                                   true);
        }
    }
}
