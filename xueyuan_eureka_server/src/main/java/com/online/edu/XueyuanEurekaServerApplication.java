package com.online.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class XueyuanEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XueyuanEurekaServerApplication.class, args);
    }

}
