package com.online.edu.eduservice.mapper;

import com.online.edu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.eduservice.entity.dto.CourseFourTableDto;
import com.online.edu.eduservice.entity.dto.CourseItemsAllDto;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //根据课程id查询课程详细信息，涉及四张表
    CourseFourTableDto getCourseFourTableDto(String courseId);

    //通过课程id查询课程详情信息，包括课程分类表，课程讲师表，课程描述表，课程表
    CourseItemsAllDto getCourseItemsAllInfo(String courseId);

}
