package com.online.edu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.statistics.entity.Daily;
import com.online.edu.statistics.fegin.IUcenterServiceFegin;
import com.online.edu.statistics.mapper.DailyMapper;
import com.online.edu.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-03-24
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private IUcenterServiceFegin iUcenterServiceFegin;

    @Override
    public Integer getMemberRegisterCountStatisticService(String day) {

        //判断统计分析表中是否存在添加天数（date_calculated）的记录
        deleteStatisticsDaily(day);

        R countStatistics = iUcenterServiceFegin.getMemberRegisterCountStatistics(day);

        Integer registerCount = (Integer) countStatistics.getData().get("memberRegisterCount");

        //将某一天中注册人数数量存入到数据库中
        // TODO 其他统计值先用随机数代替
        //获取统计信息
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        //创建统计对象
        Daily daily = new Daily();
        daily.setRegisterNum(registerCount);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);

        return registerCount;
    }

    //判断统计分析表中是否存在添加天数（date_calculated）的记录，
    // 若有，则应先删除后添加，不然会存在数据库多条信息的数据，没有则直接添加
    private void deleteStatisticsDaily(String date_calculated){

        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date_calculated);
        Integer count = baseMapper.selectCount(wrapper);

        if (count>0){
            baseMapper.delete(wrapper);
        }
    }
}
