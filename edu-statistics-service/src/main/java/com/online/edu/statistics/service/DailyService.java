package com.online.edu.statistics.service;

import com.online.edu.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
public interface DailyService extends IService<Daily> {

    Integer getMemberRegisterCountStatisticService(String day);
}
