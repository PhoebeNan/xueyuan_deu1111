package com.online.edu.eduservice.service.impl;

import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.exception.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    //添加课程信息的方法
    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {

        //1.课程基本信息到课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int resultEduCourse = baseMapper.insert(eduCourse);

        //课程ID
        String courseId = eduCourse.getId();

        if (resultEduCourse > 0) {
            //2.课程描述信息添加到课程描述表
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setDescription(courseInfoForm.getDescription());
            eduCourseDescription.setId(courseId);

            boolean saveDesc = eduCourseDescriptionService.save(eduCourseDescription);
            if (saveDesc) {
                return courseId;
            } else {
                return null;
            }
        } else {
            //添加课程失败
            throw new EduException(20001, "添加课程失败");
        }

    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {

        //查询两张表
        //1.根据课程id查询课程信息
        EduCourse eduCourse = baseMapper.selectById(id);
        if (eduCourse == null) {
            throw new EduException(20001, "课程信息不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);

        //2.根据课程id查询课程描述表信息
        EduCourseDescription courseDescriptionServiceById = eduCourseDescriptionService.getById(id);

        courseInfoForm.setDescription(courseDescriptionServiceById.getDescription());

        return courseInfoForm;
    }

    @Override
    public boolean updateCourseInfo(CourseInfoForm courseInfoForm) {

        //修改课程信息基本表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int updateRes = baseMapper.updateById(eduCourse);

        if (updateRes == 0) {
            throw new EduException(20001, "修改分类失败");
        }
        //修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        String id = courseInfoForm.getId();
        String description = courseInfoForm.getDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(description);
        return eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public List<EduCourse> getAllCourse() {

        return baseMapper.selectList(null);
    }
}
