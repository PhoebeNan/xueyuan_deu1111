package com.online.edu.eduservice.fegin;

import com.online.edu.common.R;
import com.online.edu.eduservice.fegin.config.EduServiceFeignConfig;
import com.online.edu.eduservice.fegin.hystrix.HystrixFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author NanCoder
 * @create 2020-03-24-10:31
 */
//value为服务名，FeignConfig为feign client的配置类
@FeignClient(name = "xueyuan-videoservice",
        configuration = EduServiceFeignConfig.class,
        fallback = HystrixFallBackImpl.class)
@Component
public interface IVodClienFegin {

    //删除阿里云单个视频
    @DeleteMapping(value = "/vidservice/vod/{deleteVideoId}")
    R removeVideoByVideoId(@PathVariable("deleteVideoId") String deleteVideoId);


    //删除阿里云多个个视频，
    @DeleteMapping(value = "/vidservice/vod/removeVideoList")
    R removeVideoByVideoIdBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
