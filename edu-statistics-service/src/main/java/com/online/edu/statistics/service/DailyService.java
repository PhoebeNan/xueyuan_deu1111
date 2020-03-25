package com.online.edu.statistics.service;

import com.online.edu.statistics.dto.SearchStatisticsObjDto;
import com.online.edu.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
public interface DailyService extends IService<Daily> {

    /**
     * 使用fegin调用xueyuan-ucenter-service微服务模块向数据库中插入统计一天中注册人数的数据
     * @param day
     * @return
     */
    Integer getMemberRegisterCountStatisticService(String day);

    /**
     * 前端传来查询条件，后端传回一个map，其中包含了统计日期集合(List)和注册人数或登录人数或其他统计信息集合(List)
     * @param searchStatisticsObj
     * @return
     */
    Map<String, Object> getStatisticsCountMap(SearchStatisticsObjDto searchStatisticsObj);
}
