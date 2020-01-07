package com.online.edu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.eduservice.entity.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 多条件组合分页查询讲师列表
     * @param page
     * @param queryTeacher
     */
    void getMoreConditionPageList(Page<EduTeacher> page, QueryTeacher queryTeacher);
}
