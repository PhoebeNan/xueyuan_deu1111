package com.online.edu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.statistics.dto.SearchStatisticsObjDto;
import com.online.edu.statistics.entity.Daily;
import com.online.edu.statistics.fegin.IUcenterServiceFegin;
import com.online.edu.statistics.mapper.DailyMapper;
import com.online.edu.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getStatisticsCountMap(SearchStatisticsObjDto searchStatisticsObj) {

        QueryWrapper<Daily> wrapper = new QueryWrapper<>();

        //构建统计日期的条件：date_calculated>=2019-01-10 && date_calculated<=2019-01-10
        wrapper.between("date_calculated", searchStatisticsObj.getBegin(), searchStatisticsObj.getEnd());
        //构建查询列的条件： date_calculated和register_num或者其他login_num列(不固定)
        String statisticsType = searchStatisticsObj.getType(); //由前端传来的值决定，与数据库的字段保持一致

        //sql语句查询的列只查询此两列
        wrapper.select("date_calculated",statisticsType);

        wrapper.orderByAsc("date_calculated");

        //此list集合包含了两列数据
        List<Daily> dailyList = baseMapper.selectList(wrapper);

        //构建统计日期集合
        List<String> dateCalculatedList = new ArrayList<>();
        //构建统计数据集合
        List<Integer> statisticsDataList = new ArrayList<>();

        for (Daily daily : dailyList) {
            //获取统计日期
            String dateCalculated = daily.getDateCalculated();
            dateCalculatedList.add(dateCalculated);

            //判断前端传过来想查询哪种类型的数据。对应着：register_num，login_num，video_view_num，course_num
            switch (statisticsType) {
                case "register_num":
                    statisticsDataList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    statisticsDataList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    statisticsDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    statisticsDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        //构建map集合，将统计日期集合和统计数据集合添加到map集合中并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("dateCalculatedList",dateCalculatedList);
        map.put("statisticsDataList",statisticsDataList);

        return map;
    }

    //判断统计分析表中是否存在添加天数（date_calculated）的记录，
    // 若有，则应先删除后添加，不然会存在数据库多条信息的数据，没有则直接添加
    private void deleteStatisticsDaily(String date_calculated) {

        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date_calculated);
        Integer count = baseMapper.selectCount(wrapper);

        if (count > 0) {
            baseMapper.delete(wrapper);
        }
    }
}
