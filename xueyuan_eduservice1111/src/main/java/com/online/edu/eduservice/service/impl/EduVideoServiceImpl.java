package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.mapper.EduVideoMapper;
import com.online.edu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public void deleteVideo(String id) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);

        baseMapper.delete(wrapper);
    }

    //根据课程id查询小节
    @Override
    public List<EduVideo> getVideoListByCourseId(String courseId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);

        return baseMapper.selectList(wrapper);
    }

    //根据课程id删除小节
    @Override
    public boolean removeVideoById(String videoId) {

        //TODO 删除小节时，还需要删除阿里云里面的视频
        int deleteById = baseMapper.deleteById(videoId);

        return deleteById > 0;
    }
}
