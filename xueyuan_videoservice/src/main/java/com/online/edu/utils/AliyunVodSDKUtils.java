package com.online.edu.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteStreamRequest;
import com.aliyuncs.vod.model.v20170321.DeleteStreamResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;

/**
 * 阿里云视频点播sdk初始化操作类
 * @author zhaoyanan
 * @create 2020-02-18-13:00
 */
public class AliyunVodSDKUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 删除媒体流函数
     * @param client 发送请求客户端
     * @return DeleteMezzaninesResponse 删除媒体流响应数据
     * @throws Exception
     */
    public static DeleteStreamResponse deleteStream(DefaultAcsClient client,String videoId) throws Exception {
        DeleteStreamRequest request = new DeleteStreamRequest();
        request.setVideoId(videoId);
        return client.getAcsResponse(request);
    }

    /**
     * 删除视频
     * @param client 发送请求客户端
     * @return DeleteVideoResponse 删除视频响应数据
     * @throws Exception
     */
    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client,String videoIds) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoIds);
        return client.getAcsResponse(request);
    }

}
