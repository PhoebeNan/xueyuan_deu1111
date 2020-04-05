package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.QueryTeacher;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 多条件组合分页查询讲师列表
     * @param page
     * @param queryTeacher
     */
    void getMoreConditionPageList(Page<EduTeacher> page, QueryTeacher queryTeacher);

    /**
     * 通过id删除一个讲师
     * @param id
     * @return
     */
    boolean deleteTeacherById(String id);

    /**
     * 前台系统实现讲师分页功能
     * @param paramPage
     * @return
     */
    HashMap<String, Object> getFrontTeachersMap(Page<EduTeacher> paramPage);

    /**
     * 通过讲师id查询课程列表
     * @param teacherId
     * @return
     */
    List<EduCourse> getCourseListByTeacherId(Long teacherId);
}
