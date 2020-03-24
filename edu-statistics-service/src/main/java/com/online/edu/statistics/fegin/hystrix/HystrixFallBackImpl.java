package com.online.edu.statistics.fegin.hystrix;

import com.online.edu.common.R;
import com.online.edu.statistics.fegin.IUcenterServiceFegin;
import org.springframework.stereotype.Component;

/**
 * @author NanCoder
 * @create 2020-03-24-10:40
 */
@Component
public class HystrixFallBackImpl implements IUcenterServiceFegin {

    @Override
    public R
    getMemberRegisterCountStatistics(String day) {
        return R.error().data("xueyuan-statistics-service-fallback","error，使用fegin+hystrix实现了熔断处理");
    }
}
