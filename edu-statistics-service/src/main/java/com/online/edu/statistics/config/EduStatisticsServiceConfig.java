package com.online.edu.statistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Component
@MapperScan("com.online.edu.statistics.mapper")
public class EduStatisticsServiceConfig {


}
