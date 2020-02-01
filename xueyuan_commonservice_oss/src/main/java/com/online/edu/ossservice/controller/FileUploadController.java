package com.online.edu.ossservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.common.R;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.online.edu.ossservice.handler.ConstantPropertiesUtil;

/**
 * @author zhaoyanan
 * @create 2020-02-01-13:50
 */
@RequestMapping
@RestController("/eduservice/oss")
@CrossOrigin(allowCredentials = "true")
public class FileUploadController {

    @PostMapping("upload")
    public R fileUpload(@RequestParam("file") MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //获取文件的名称
            String originalFilename = file.getOriginalFilename();

            //保证每个文件名称都不一样
            originalFilename = UUID.randomUUID().toString() + originalFilename;

            //获取当前日期
            String pathDate = new DateTime().toString("yyyy/MM/dd");
            originalFilename = pathDate + "/" + originalFilename;

            //获取文件流
            InputStream in = file.getInputStream();
            ossClient.putObject(bucketName, originalFilename, in);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回上传后oss存储的路径
            //https://zyn-edu-test1112.oss-cn-hangzhou.aliyuncs.com/abc/1.txt
            String path = "http://" + bucketName + "." + endpoint + "/" + originalFilename;

            return R.ok().data("imgUrl", path);

        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
