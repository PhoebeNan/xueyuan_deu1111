package com.online.edu.eduservice.controller.fron;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 前台讲师controller
 * @author NanCoder
 * @create 2020-03-26-8:20
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/fron/teacher")
public class FronEduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

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
