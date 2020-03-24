package com.online.edu.eduservice.fegin.hystrix;

import com.online.edu.common.R;
import com.online.edu.eduservice.fegin.IVodClienFegin;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NanCoder
 * @create 2020-03-24-10:40
 */
@Component
public class HystrixFallBackImpl implements IVodClienFegin {

    @Override
    public R removeVideoByVideoId(String deleteVideoId) {
        return R.error().data("errorHystrixSingleton","单个视频删除失败，使用fegin+hystrix实现了熔断处理");
    }

    @Override
    public R removeVideoByVideoIdBatch(List<String> videoIdList) {
        return R.error().data("errorHystrixBatch","批量删除视频失败，使用fegin+hystrix实现了熔断处理");
    }
}
