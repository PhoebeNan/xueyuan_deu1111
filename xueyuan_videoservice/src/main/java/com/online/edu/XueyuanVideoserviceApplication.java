package com.online.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //排除需要加载数据库
@EnableEurekaClient
@CrossOrigin
public class XueyuanVideoserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XueyuanVideoserviceApplication.class, args);
    }

}
