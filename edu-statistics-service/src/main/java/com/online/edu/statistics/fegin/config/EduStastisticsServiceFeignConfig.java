package com.online.edu.statistics.fegin.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author NanCoder
 * @create 2020-03-24-10:33
 */
@Component
public class EduStastisticsServiceFeignConfig {

    //注入Retryer的bean，feign在远程调用服务失败后会进行重试
    @Bean
    private Retryer feginRetryer(){
        return new Retryer.Default();
    }
}
