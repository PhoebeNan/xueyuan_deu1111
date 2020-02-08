package com.online.edu.eduservice.service;

import com.online.edu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.dto.OneSubjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-02-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 通过上传文件，使用poi导入excel一级分类
     * @param file
     */
    List<String> importPoiSubject(MultipartFile file);

    /**
     * 返回所有课程分类数据
     * @return
     */
    List<OneSubjectDto> getAllSubjectList();

    /**
     * 删除一级分类
     * @param id
     * @return
     */
    boolean deleteSubjectById(String id);

    /**
     * 添加一级分类
     * @param eduSubject
     * @return
     */
    boolean addOneLevel(EduSubject eduSubject);

    /**
     * 添加二级分类
     * @param eduSubject
     * @return
     */
    boolean addTwoLevel(EduSubject eduSubject);
}
