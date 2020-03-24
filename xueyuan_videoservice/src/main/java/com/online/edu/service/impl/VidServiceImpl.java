package com.online.edu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.online.edu.service.VidService;
import com.online.edu.utils.AliyunVodSDKUtils;
import com.online.edu.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author NanCoder
 * @create 2020-03-23-10:15
 */
@Service
public class VidServiceImpl implements VidService {

    @Override
    public String uploadAliyunVideo(MultipartFile file) {

        //获取文件名称
        String fileName = file.getOriginalFilename();

        //获取文件名字
        String title = fileName.substring(0, fileName.lastIndexOf("."));

        //获取文件流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                title, fileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

        //返回videoId
        String videoId = null;

        if (response.isSuccess()) {

            videoId = response.getVideoId();
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            videoId = response.getVideoId();
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

        return videoId;
    }

    @Override
    public void deleteVideoIdService(String deleteVideoId) {

        DeleteVideoResponse response = null;
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            response = new DeleteVideoResponse();

            response = AliyunVodSDKUtils.deleteVideo(client, deleteVideoId);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        assert response != null;
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    @Override
    public void deleteVideoIdBatch(List<String> videoIdList) {

        DeleteVideoResponse response = null;
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            response = new DeleteVideoResponse();

            response = AliyunVodSDKUtils.deleteVideoBatch(client, videoIdList);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        assert response != null;
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
