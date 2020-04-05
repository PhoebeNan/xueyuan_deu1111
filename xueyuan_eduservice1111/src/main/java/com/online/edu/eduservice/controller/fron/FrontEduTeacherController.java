package com.online.edu.eduservice.controller.fron;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 前台讲师controller
 * @author NanCoder
 * @create 2020-03-26-8:20
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/front/teacher")
public class FrontEduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    //根据讲师id查询课程详情，包括讲师表和讲师所关联的课程表
    @ApiOperation(value = "讲师详情")
    @GetMapping("{teacherId}")
    public R getFrontTeacherItemByTeacherId(@PathVariable("teacherId") Long teacherId){

        //查询讲师表详情信息
        EduTeacher eduTeacher =eduTeacherService.getById(teacherId);

        //查询讲师所关联的课程表,一个讲师可能会关联多张课程表
        List<EduCourse> courseList =eduTeacherService.getCourseListByTeacherId(teacherId);

        return R.ok().data("eduTeacher",eduTeacher).data("courseList",courseList);

    }


    //(currentPage-1)*pageSize  :   从哪个索引开始查
    //pageSize  :   每页有多少条数
    //select * from user limit (currentPage-1)*pageSize,pageSize
    //计算总页数公式：totalPage = (totalRecord + pageSize -1)/pageSize  totalPage进行取整计算

    //前台系统实现讲师分页功能
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{currentPage}/{pageSize}")
    public R getFrontTeachersMap(@PathVariable("currentPage")
                                 @ApiParam(name = "currentPage",value = "当前页",required = true)
                                             Long currentPage,
                                 @PathVariable("pageSize")
                                 @ApiParam(name = "pageSize",value = "每页有条数",required = true)
                                             Long pageSize){

        Page<EduTeacher> paramPage = new Page<>(currentPage,pageSize);
        HashMap<String, Object> map = eduTeacherService.getFrontTeachersMap(paramPage);

        return R.ok().data(map);
    }

}
