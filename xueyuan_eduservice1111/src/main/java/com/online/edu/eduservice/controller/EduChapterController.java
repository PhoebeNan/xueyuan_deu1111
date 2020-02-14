package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //根据课程id查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoList(@PathVariable String courseId){

        List<EduChapterDto> eduChapterVideoList = eduChapterService.getChapterVideoList(courseId);

        return R.ok().data("items",eduChapterVideoList);
    }
}

