package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-13
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节，小节表中存储的有课程id
     * @param id
     */
    void deleteVideo(String id);

    /**
     * 根据课程id查询小节
     * @param courseId
     * @return
     */
    List<EduVideo> getVideoListByCourseId(String courseId);

    /**
     * 根据课程id删除小节
     * @param videoId
     * @return
     */
    boolean removeVideoById(String videoId);
}
