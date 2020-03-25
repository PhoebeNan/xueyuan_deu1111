package com.online.edu.statistics.controller;


import com.online.edu.common.R;
import com.online.edu.statistics.dto.SearchStatisticsObjDto;
import com.online.edu.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class DailyController {

    @Autowired
    private DailyService dailyService;

    //1.使用fegin调用xueyuan-ucenter-service微服务模块向数据库中插入统计一天中注册人数的数据
    @GetMapping("memberRegisterCount/{day}")
    public R getMemberRegisterCountStatisticController(@PathVariable("day") String day){

        Integer memberRegisterCount = dailyService.getMemberRegisterCountStatisticService(day);

        return R.ok().data("memberRegisterCount",memberRegisterCount);
    }

    //2.前端传来查询条件，后端传回一个map，其中包含了统计日期集合(List)和注册人数或登录人数或其他统计信息集合(List)
    @PostMapping("getStatisticsCountMap")
    public R getStatisticsCountMap(@RequestBody SearchStatisticsObjDto searchStatisticsObj){

        Map<String,Object> statisticsCountMap = dailyService.getStatisticsCountMap(searchStatisticsObj);

        return R.ok().data(statisticsCountMap);
    }

}

