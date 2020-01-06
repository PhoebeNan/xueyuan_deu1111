package com.online.edu.eduservice.controller;


import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
//@RestController
//@RequestMapping("/eduservice/teacher")
@Controller
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;
    /**
     * 查询所有讲师
     * @return
     */
    //@GetMapping("eduservice/teacher")
    public List<EduTeacher> getAllEduTeacherList(){

        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);

        return eduTeacherList;
    }

    @RequestMapping("teacher")
    public String test(){
        String s = "11";
        return s;
    }
}

