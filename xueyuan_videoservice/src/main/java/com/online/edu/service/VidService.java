package com.online.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author NanCoder
 * @create 2020-03-23-10:14
 */
public interface VidService {

    String uploadAliyunVideo(MultipartFile file);

    void deleteVideoIdService(String deleteVideoId);

    void deleteVideoIdBatch(List<String> videoIdList);
}
