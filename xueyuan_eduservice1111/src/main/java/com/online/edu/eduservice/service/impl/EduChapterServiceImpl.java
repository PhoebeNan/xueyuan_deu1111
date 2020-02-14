package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.eduservice.entity.EduChapter;
import com.online.edu.eduservice.entity.EduVideo;
import com.online.edu.eduservice.entity.dto.EduChapterDto;
import com.online.edu.eduservice.entity.dto.EduVideoDto;
import com.online.edu.eduservice.exception.EduException;
import com.online.edu.eduservice.mapper.EduChapterMapper;
import com.online.edu.eduservice.service.EduChapterService;
import com.online.edu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-13
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    //课程列表页面中的删除方法
    @Override
    public void deleteChapter(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);

        baseMapper.delete(wrapper);
    }

    //根据课程id查询章节和小节
    @Override
    public List<EduChapterDto> getChapterVideoList(String courseId) {

        //根据课程id查询课程下的所有章节
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapper1);

        //根据课程id查询课程下的所有小节
        List<EduVideo> eduVideoList = eduVideoService.getVideoListByCourseId(courseId);

        List<EduChapterDto> chapterDtos = new ArrayList<>();

        //不用对eduChapterList继续非空判断，因为课程下的章节和小节可能为空
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(eduChapter, eduChapterDto);

            //将每一个章节dto对象存放到list集合中
            //集合中存储的是对象的引用，相当于指针，key中是对象的首地址，
            //当已经添加对象到list集合中去后，在改变其对象中的属性值，
            //则list集合中的对象依然会改变。
            chapterDtos.add(eduChapterDto);

            List<EduVideoDto> videoDtos = new ArrayList<>();

            for (int i1 = 0; i1 < eduVideoList.size(); i1++) {

                EduVideo eduVideo = eduVideoList.get(i1);

                //判断每个小节与正在遍历章节的id是否相等，若相等，则进行存储list操作
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    EduVideoDto eduVideoDto = new EduVideoDto();
                    BeanUtils.copyProperties(eduVideo, eduVideoDto);

                    //将每一个小节dto对象存放到list集合中
                    videoDtos.add(eduVideoDto);
                }
            }
            //为每个章节dto对象设置chlid属性，即videoDtos集合
            eduChapterDto.setChildren(videoDtos);
        }

        return chapterDtos;
    }

    //自定义删除方法，如果章节中有小节则不删除
    //发布课程中创建课程大纲中的删除方法
    @Override
    public boolean removeChapterById(String chapterId) {

        //判断章节里面是否有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0) {
            //有小节，不删除
            throw new EduException(20001, "删除失败");
        }
        //若没有小节，则进行删除章节
        int deleteById = baseMapper.deleteById(chapterId);

        return deleteById > 0;
    }
}
