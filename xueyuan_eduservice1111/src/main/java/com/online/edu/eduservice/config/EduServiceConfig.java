package com.online.edu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Component
@MapperScan("com.online.edu.eduservice.mapper")
public class EduServiceConfig {

}
