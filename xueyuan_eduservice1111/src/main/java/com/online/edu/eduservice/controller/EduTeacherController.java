package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Controller
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 3.分页查询讲师列表
     * @param currentPage
     * @param size
     * @return
     */
    @GetMapping("pageList/{currentPage}/{size}")
    public R getPageAllEduTeacherList(@PathVariable Long currentPage,@PathVariable Long size){

        Page<EduTeacher> pageTeacher = new Page<>(currentPage, size);
        eduTeacherService.page(pageTeacher, null);

        long pages = pageTeacher.getPages(); //总页数
        long totalTeachers = pageTeacher.getTotal(); //总条数
        List<EduTeacher> teacherRecords = pageTeacher.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("pages",pages);
        map.put("totalTeachers",totalTeachers);
        map.put("teacherRecords",teacherRecords);

        return R.ok().data(map);
    }

    /**
     * 2.通过id删除一个讲师
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable String id){

        boolean b = eduTeacherService.removeById(id);
        return b;
    }

    /**
     * 1.查询所有讲师
     * @return
     */
    @GetMapping
    public R getAllEduTeacherList(){

        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);

        return R.ok().data("item", eduTeacherList);
    }

}

