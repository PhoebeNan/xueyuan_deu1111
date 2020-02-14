package com.online.edu.eduservice.controller;


import com.online.edu.common.R;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //4.根据id查询
    @GetMapping("getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);

        return R.ok().data("eduVideo",eduVideo);
    }

    //3.删除小节
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        boolean eduVideo = eduVideoService.removeVideoById(videoId);

        if(eduVideo){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //2.编辑小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){

        boolean update = eduVideoService.updateById(eduVideo);

        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //1.添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){

        boolean save = eduVideoService.save(eduVideo);

        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

