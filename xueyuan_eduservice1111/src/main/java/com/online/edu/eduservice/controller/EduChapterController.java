package com.online.edu.eduservice.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.handler.ConstantPropertiesUtil;
import com.online.edu.eduservice.service.EduChapterService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;



    //5.删除章节的方法
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){

        //自定义删除方法，如果章节中有小节则不删除
        boolean removeChapterById =eduChapterService.removeChapterById(chapterId);
        if(removeChapterById){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //4.修改章节的方法
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){

        boolean updateById = eduChapterService.updateById(eduChapter);
        if(updateById){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //3.根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){

        EduChapter eduChapter = eduChapterService.getById(chapterId);

        return R.ok().data("eduChapter",eduChapter);
    }

    //2.添加章节的方法
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){

        boolean save = eduChapterService.save(eduChapter);

        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //1.根据课程id查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoList(@PathVariable String courseId){

        List<EduChapterDto> eduChapterVideoList = eduChapterService.getChapterVideoList(courseId);

        return R.ok().data("items",eduChapterVideoList);
    }
}

