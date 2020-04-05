package com.online.edu.eduservice.controller.fron;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.dto.CourseItemsAllDto;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 前台讲师controller
 *
 * @author NanCoder
 * @create 2020-03-26-8:20
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/front/course")
public class FrontEduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    //根据课程id查询课程详情，包括讲师表和课程表，章节，小节
    @ApiOperation(value = "课程详情")
    @GetMapping("{courseId}")
    public R getFrontCourseItemByCourseId(@PathVariable("courseId") String courseId) {

        //1.通过课程id查询课程详情信息，包括课程分类表，课程讲师表，课程描述表，课程表

        CourseItemsAllDto courseItemsAllInfo = eduCourseService.getCourseItemsAllInfo(courseId);

        //2.根据课程id查询课程里面所有的章节，章节里面包括所有小节
        List<EduChapterDto> chapterVideoList = eduChapterService.getChapterVideoList(courseId);

        return R.ok().data("courseItemsAllInfo",courseItemsAllInfo).data("chapterVideoList",chapterVideoList);
    }


    //(currentPage-1)*pageSize  :   从哪个索引开始查
    //pageSize  :   每页有多少条数
    //select * from user limit (currentPage-1)*pageSize,pageSize
    //计算总页数公式：totalPage = (totalRecord + pageSize -1)/pageSize  totalPage进行取整计算

    //前台系统实现课程分页功能
    @ApiOperation(value = "分页课程列表")
    @GetMapping("{currentPage}/{pageSize}")
    public R getFrontCourseMap(@PathVariable("currentPage")
                               @ApiParam(name = "currentPage", value = "当前页", required = true)
                                       Long currentPage,
                               @PathVariable("pageSize")
                               @ApiParam(name = "pageSize", value = "每页有条数", required = true)
                                       Long pageSize) {

        Page<EduCourse> paramPage = new Page<>(currentPage, pageSize);
        HashMap<String, Object> map = eduCourseService.getFrontCourseMap(paramPage);

        return R.ok().data(map);
    }

}
