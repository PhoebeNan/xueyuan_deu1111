package com.online.edu.controller;

import com.online.edu.common.R;
import com.online.edu.service.VidService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author NanCoder
 * @create 2020-03-23-10:09
 */
@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {

    @Autowired
    private VidService vidService;

    //3.通过videoId删除阿里云服务器上的多个视频，批量删除视频，一次只能批量删20个
    @DeleteMapping("removeVideoList")
    public R deleteVideoIdBatch(@ApiParam(name = "videoIdList", value = "云端视频多个id", required = true)
                           @RequestParam("videoIdList") List<String> videoIdList){

        vidService.deleteVideoIdBatch(videoIdList);

        return R.ok();
    }

    //2.通过videoId删除阿里云服务器上的视频
    @DeleteMapping("{deleteVideoId}")
    public R deleteVideoId(@ApiParam(name = "deleteVideoId", value = "云端视频id", required = true)
                               @PathVariable String deleteVideoId){

        vidService.deleteVideoIdService(deleteVideoId);

        return R.ok();
    }

    //1.实现上传视频到阿里云服务器并返回
    @PostMapping("upload")
    public R uploadAliyunVideo(@RequestParam("file")MultipartFile file){

        //返回上传后的视频id
        String videoId = vidService.uploadAliyunVideo(file);

        return R.ok().data("videoId",videoId);
    }

}
