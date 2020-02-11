package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.form.CourseInfoForm;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息的方法
     * @param courseInfoForm
     * @return
     */
    String insertCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 根据课程id查询课程信息
     * @param id
     * @return
     */
    CourseInfoForm getCourseInfoById(String id);

    /**
     * 修改课程的方法
     * @param courseInfoForm
     * @return
     */
    boolean updateCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 查询所有的课程
     * @return
     */
    List<EduCourse> getAllCourse();

}
