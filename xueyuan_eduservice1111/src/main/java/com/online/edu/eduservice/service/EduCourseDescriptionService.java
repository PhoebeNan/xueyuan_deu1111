package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    /**
     * 根据课程id删除课程描述
     * @param id
     */
    void deleteCourseDesc(String id);
}
