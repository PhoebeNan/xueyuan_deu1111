package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.QueryTeacher;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void getMoreConditionPageList(Page<EduTeacher> page, QueryTeacher queryTeacher) {
        //判断前端传过来的参数对象queryTeacher是否为空，若为空，则分页查询全部讲师
        if (queryTeacher == null) {
            baseMapper.selectPage(page, null);
            return;
        }
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String startTime = queryTeacher.getStartTime();
        String endTime = queryTeacher.getEndTime();

        //构造 条件构造器进行拼接查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.isNotBlank(startTime)) {
            wrapper.gt("gmt_create", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            wrapper.lt("gmt_modified", endTime);
        }

        //多组合条件分页查询
        baseMapper.selectPage(page, wrapper);
    }
}
