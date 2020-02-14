package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.mapper.EduCourseDescriptionMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void deleteCourseDesc(String id) {
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);

        baseMapper.delete(wrapper);
    }
}
