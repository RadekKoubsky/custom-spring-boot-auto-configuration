package org.rkoubsky.mywebserver;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MyWebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWebServerApplication.class, args);
    }


    @Component
    static class MyWebServerRunner implements ApplicationRunner{

        private final MyWebServer webServer;

        MyWebServerRunner(MyWebServer webServer) {
            this.webServer = webServer;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            webServer.start();
        }
    }
}
