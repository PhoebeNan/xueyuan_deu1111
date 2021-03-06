package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.dto.CourseFourTableDto;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.entity.form.CourseInfoForm;
import com.online.edu.eduservice.service.EduCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-09
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //7.发布课程后修改发布的状态
    @GetMapping("updateCourseStatus/{courseId}")
    public R updateCourseStatus(@PathVariable String courseId){

        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean flag =eduCourseService.updateById(eduCourse);
        if (flag){
            //修改发布的状态成功
            return R.ok();
        }else {
            //修改发布的状态失败
            return R.error();
        }
    }

    //6.根据课程id查询课程详细信息，涉及四张表
    @GetMapping("getAllCourseFourTable/{courseId}")
    public R getAllCourseFourTable(@PathVariable String courseId){

        CourseFourTableDto courseFourTableDto =eduCourseService.getAllCourseFourTable(courseId);

        return R.ok().data("courseList",courseFourTableDto);
    }

    //5.删除课程的方法
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){

        boolean flag =eduCourseService.deleteCourseById(id);

        if (flag){
            //删除成功
            return R.ok();
        }else {
            //删除失败
            return R.error();
        }
    }

    //4.查询所有的课程
    //TODO 待完善列表带分页，条件查询
    @GetMapping("listCourse")
    public R getAllCourse(){

        List<EduCourse> eduCourseList = eduCourseService.getAllCourse();
        if (eduCourseList != null){
            //添加成功
            return R.ok().data("items", eduCourseList);
        }else {
            //添加失败
            return R.error();
        }
    }

    //3.修改课程的方法
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id,
                              @RequestBody CourseInfoForm courseInfoForm){

        boolean flag =eduCourseService.updateCourseInfo(courseInfoForm);
        if (flag){
            //添加成功
            return R.ok();
        }else {
            //添加失败
            return R.error();
        }
    }

    //2.根据课程id查询课程信息
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id){
        CourseInfoForm courseInfoForm =eduCourseService.getCourseInfoById(id);

        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //1.添加课程信息的方法
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {

        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        if(StringUtils.isNotBlank(courseId)){
            return R.ok().data("courseId", courseId);
        }else {
            return R.error().message("保存失败");
        }
    }
}

