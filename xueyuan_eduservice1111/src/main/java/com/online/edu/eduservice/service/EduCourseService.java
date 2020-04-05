package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.CourseFourTableDto;
import com.online.edu.eduservice.entity.dto.CourseItemsAllDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;

import java.util.HashMap;
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

    /**
     * 删除课程的方法
     * @param id
     * @return
     */
    boolean deleteCourseById(String id);

    /**
     * 根据课程id查询课程详细信息，涉及四张表
     * @param courseId
     * @return
     */
    CourseFourTableDto getAllCourseFourTable(String courseId);

    /**
     * 前台系统实现讲师分页功能
     * @param paramPage
     * @return
     */
    HashMap<String, Object> getFrontCourseMap(Page<EduCourse> paramPage);


    /**
     * 通过课程id查询课程详情信息，包括课程分类表，课程讲师表，课程描述表，课程表
     * @param courseId
     * @return
     */
    CourseItemsAllDto getCourseItemsAllInfo(String courseId);
}
