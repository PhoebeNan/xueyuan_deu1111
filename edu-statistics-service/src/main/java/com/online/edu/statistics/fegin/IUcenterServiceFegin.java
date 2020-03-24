package com.online.edu.statistics.fegin;

import com.online.edu.common.R;
import com.online.edu.statistics.fegin.config.EduStastisticsServiceFeignConfig;
import com.online.edu.statistics.fegin.hystrix.HystrixFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author NanCoder
 * @create 2020-03-24-15:05
 */
@FeignClient(name = "xueyuan-ucenter-service",
        configuration = EduStastisticsServiceFeignConfig.class,
        fallback = HystrixFallBackImpl.class)
@Component
public interface IUcenterServiceFegin {

    //统计一天中的注册人数
    @GetMapping("/ucenter/member/memberRegisterCount/{day}")
    R getMemberRegisterCountStatistics(@PathVariable("day") String day);

}
