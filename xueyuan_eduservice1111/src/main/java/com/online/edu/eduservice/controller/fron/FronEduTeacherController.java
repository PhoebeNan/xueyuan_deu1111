package com.online.edu.eduservice.controller.fron;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台讲师controller
 * @author NanCoder
 * @create 2020-03-26-8:20
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/fron/teacher")
public class FronEduTeacherController {

    //(currentPage-1)*pageSize  :   从哪个索引开始查
    //pageSize  :   每页有多少条数
    //select * from user limit (currentPage-1)*pageSize,pageSize
    //计算总页数公式：totalPage = (totalRecord + pageSize -1)/pageSize  totalPage进行取整计算


}
