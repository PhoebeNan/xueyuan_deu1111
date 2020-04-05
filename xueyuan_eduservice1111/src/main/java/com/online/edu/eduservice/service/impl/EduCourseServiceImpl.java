package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduCourseDescription;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.dto.CourseFourTableDto;
import com.online.edu.eduservice.entity.dto.CourseItemsAllDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.exception.EduException;
import com.online.edu.eduservice.mapper.EduCourseMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseDescriptionService;
import com.online.edu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;



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

    @Override
    public boolean deleteCourseById(String id) {

        //根据课程id删除小节，小节表中存储的有课程id
        eduVideoService.deleteVideo(id);

        //根据课程id删除章节
        eduChapterService.deleteChapter(id);

        //根据课程id删除课程描述
        eduCourseDescriptionService.deleteCourseDesc(id);

        //根据课程id删除课程本身
        int resRow = baseMapper.deleteById(id);

        return resRow > 0;
    }

    //根据课程id查询课程详细信息，涉及四张表
    @Override
    public CourseFourTableDto getAllCourseFourTable(String courseId) {

        return baseMapper.getCourseFourTableDto(courseId);
    }

    @Override
    public HashMap<String, Object> getFrontCourseMap(Page<EduCourse> paramPage) {


        baseMapper.selectPage(paramPage,null);

        HashMap<String, Object> frontCourseMap = new HashMap<>();

        long pageCurrent = paramPage.getCurrent();//当前页码
        long pageSize = paramPage.getSize();//每页有多少条数据
        long totalPage = paramPage.getPages();//总页数
        long totalNums = paramPage.getTotal();//总条数
        List<EduCourse> totalRecords = paramPage.getRecords();//总记录数，所有记录数
        boolean previous = paramPage.hasPrevious();//是否有前一页
        boolean next = paramPage.hasNext();//是否有后一页


        frontCourseMap.put("pageCurrent",pageCurrent);
        frontCourseMap.put("pageSize",pageSize);
        frontCourseMap.put("totalPage",totalPage);
        frontCourseMap.put("totalNums",totalNums);
        frontCourseMap.put("totalRecords",totalRecords);
        frontCourseMap.put("previous",previous);
        frontCourseMap.put("next",next);

        return frontCourseMap;
    }

    @Override
    public CourseItemsAllDto getCourseItemsAllInfo(String courseId) {

        return baseMapper.getCourseItemsAllInfo(courseId);
    }
}
