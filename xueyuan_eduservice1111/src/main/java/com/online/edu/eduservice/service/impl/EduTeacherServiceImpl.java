package com.online.edu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.eduservice.entity.EduCourse;
import com.online.edu.eduservice.entity.EduTeacher;
import com.online.edu.eduservice.entity.QueryTeacher;
import com.online.edu.eduservice.mapper.EduTeacherMapper;
import com.online.edu.eduservice.service.EduCourseService;
import com.online.edu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 赵亚楠
 * @since 2020-01-06
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public void getMoreConditionPageList(Page<EduTeacher> page, QueryTeacher queryTeacher) {
        //判断前端传过来的参数对象queryTeacher是否为空，若为空，则分页查询全部讲师
        if (queryTeacher == null) {
            baseMapper.selectPage(page, null);
            return;
        }
        String name = queryTeacher.getName();
        String level = queryTeacher.getLevel();
        String startTime = queryTeacher.getBegin();
        String endTime = queryTeacher.getEnd();

        //构造 条件构造器进行拼接查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(level)) {
            wrapper.eq("level", level);
        }
        if (StringUtils.isNotBlank(startTime)) {
            wrapper.gt("gmt_create", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            wrapper.lt("gmt_modified", endTime);
        }

        //多组合条件分页查询
        baseMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean deleteTeacherById(String id) {

        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public HashMap<String, Object> getFrontTeachersMap(Page<EduTeacher> paramPage) {

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        baseMapper.selectPage(paramPage,wrapper);

        HashMap<String, Object> fronTeachersMap = new HashMap<>();

        long pageCurrent = paramPage.getCurrent();//当前页码
        long pageSize = paramPage.getSize();//每页有多少条数据
        long totalPage = paramPage.getPages();//总页数
        long totalNums = paramPage.getTotal();//总条数
        List<EduTeacher> totalRecords = paramPage.getRecords();//总记录数，所有记录数
        boolean previous = paramPage.hasPrevious();//是否有前一页
        boolean next = paramPage.hasNext();//是否有后一页


        fronTeachersMap.put("pageCurrent",pageCurrent);
        fronTeachersMap.put("pageSize",pageSize);
        fronTeachersMap.put("totalPage",totalPage);
        fronTeachersMap.put("totalNums",totalNums);
        fronTeachersMap.put("totalRecords",totalRecords);
        fronTeachersMap.put("previous",previous);
        fronTeachersMap.put("next",next);

        return fronTeachersMap;
    }

    @Override
    public List<EduCourse> getCourseListByTeacherId(Long teacherId) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //构建条件
        wrapper.eq("teacher_id",teacherId);
        wrapper.orderByAsc("gmt_modified");

        List<EduCourse> courseList = eduCourseService.list(wrapper);

        return courseList;
    }
}
