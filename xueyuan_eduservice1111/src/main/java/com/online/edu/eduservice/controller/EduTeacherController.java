package com.online.edu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.QueryTeacher;
import com.online.edu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){

        boolean save = eduTeacherService.save(teacher);

        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 多条件组合分页查询讲师列表,@RequestBody前台传入的是json格式数据
     * 它必须为post提交，没有required = false时对象里面必须不为空
     * @param currentPage
     * @param size
     * @param queryTeacher
     * @return
     */
    @PostMapping("moreConditionPageList/{currentPage}/{size}")
    public R getMoreConditionPageList(@PathVariable Long currentPage,
                                      @PathVariable Long size,
                                      @RequestBody(required = false) QueryTeacher queryTeacher){

        Page<EduTeacher> page = new Page<>(currentPage, size);

        //调用service业务层代码，进行条件查询
        eduTeacherService.getMoreConditionPageList(page,queryTeacher);

        return getPages(page);
    }

    private static R getPages(Page<EduTeacher> page) {
        long pages = page.getPages(); //总页数
        long totalTeachers = page.getTotal(); //总条数
        List<EduTeacher> teacherRecords = page.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("pages",pages);
        map.put("totalTeachers",totalTeachers);
        map.put("teacherRecords",teacherRecords);

        return R.ok().data(map);
    }

    /**
     * 3.分页查询讲师列表
     * @param currentPage
     * @param size
     * @return
     */
    @GetMapping("pageList/{currentPage}/{size}")
    public R getPageAllEduTeacherList(@PathVariable Long currentPage, @PathVariable Long size){

        Page<EduTeacher> pageTeacher = new Page<>(currentPage, size);
        eduTeacherService.page(pageTeacher, null);

        return getPages(pageTeacher);
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

