package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduSubject;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import com.online.edu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-08
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //5.添加二级分类
    @PostMapping("addTwoLevel")
    public R addTwoLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.addTwoLevel(eduSubject);
        if (flag){
            //添加成功
            return R.ok();
        }else {
            //添加失败
            return R.error();
        }
    }

    //4.添加一级分类
    @PostMapping("addOneLevel")
    public R addOneLevel(@RequestBody EduSubject eduSubject){
        boolean flag = eduSubjectService.addOneLevel(eduSubject);
        if (flag){
            //添加成功
            return R.ok();
        }else {
            //添加失败
            return R.error();
        }
    }

    //3.删除一级分类
    @DeleteMapping("{id}")
    public R deleteSubjectById(@PathVariable String id){
        boolean flag = eduSubjectService.deleteSubjectById(id);
        if (flag){
            //删除成功
            return R.ok();
        }else {
            //删除失败
            return R.error();
        }
    }

    //2.返回所有课程分类数据
    @GetMapping
    public R getAllSubjectList(){
        List<OneSubjectDto> list = eduSubjectService.getAllSubjectList();
        return R.ok().data("OneSubjectDto",list);
    }

    //1.通过上传excel文件获取文件的内容
    @PostMapping("import")
    public R importPoiSubject(@RequestParam("file") MultipartFile file) {

        List<String> msgPrompt = eduSubjectService.importPoiSubject(file);

        if (msgPrompt.size() == 0) {
            return R.ok().message("批量导入成功");
        } else {
            //有数据，表示有错误信息
            return R.error().message("部分数据导入失败").data("msgList", msgPrompt);
        }
    }
}

