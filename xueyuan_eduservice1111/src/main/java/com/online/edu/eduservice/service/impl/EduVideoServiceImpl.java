package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.fegin.IVodClienFegin;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-13
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    //注入IVodClienFegin
    @Autowired
    private IVodClienFegin iVodClienFegin;

    //在删除章节中，删除小节前删除属于小节的多个视频
    @Override
    public void deleteVideo(String courseId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);

        //先批量删除多个阿里云上多个视频
        iVodClienFegin.removeVideoByVideoIdBatch(selectVideIdsByCourseId(courseId));

        baseMapper.delete(wrapper);
    }

    //通过小节id查询数据库获取视频id
    private List<String> selectVideIdsByCourseId(String courseId) {

        List<String> videoSourceIds = new ArrayList<>();

        //获取视频id集合，通过查询课程id得到
        List<EduVideo> videoListByCourseId = getVideoListByCourseId(courseId);
        for (EduVideo eduVideo : videoListByCourseId) {
            String videoSourceId = eduVideo.getVideoSourceId();
            videoSourceIds.add(videoSourceId);
        }

        return videoSourceIds;
    }

    //根据课程id查询小节
    @Override
    public List<EduVideo> getVideoListByCourseId(String courseId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);

        return baseMapper.selectList(wrapper);
    }

    //根据小节id删除小节
    @Override
    public boolean removeVideoById(String xiaoJieId) {

        //TODO 删除小节时，还需要删除阿里云里面的视频
        String videoSourceId = selectVideIdByXiaoJieId(xiaoJieId);

        if (!StringUtils.isBlank(videoSourceId)) {
            iVodClienFegin.removeVideoByVideoId(videoSourceId);
        }

        int deleteById = baseMapper.deleteById(xiaoJieId);

        return deleteById > 0;
    }

    //通过小节id查询数据库获取视频id
    private String selectVideIdByXiaoJieId(String xiaoJieId) {

        //获取视频id，通过查询数据库得到
        EduVideo eduVideo = baseMapper.selectById(xiaoJieId);

        return eduVideo.getVideoSourceId();
    }
}
