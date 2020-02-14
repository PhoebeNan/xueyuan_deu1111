package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.EduChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-13
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程id删除章节
     * @param id
     */
    void deleteChapter(String id);

    /**
     * 根据课程id查询章节和小节
     * @param courseId
     * @return
     */
    List<EduChapterDto> getChapterVideoList(String courseId);
}
