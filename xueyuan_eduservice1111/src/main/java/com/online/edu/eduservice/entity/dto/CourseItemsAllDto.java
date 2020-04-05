package com.online.edu.eduservice.entity.dto;

import lombok.Data;

/**
    # 1.通过课程id查询课程详情信息，包括课程分类表，课程讲师表，课程描述表，课程表
     SELECT
         c.id,
         c.title,
         c.price,
         c.cover,
         c.lesson_num,
         c.buy_count,
         c.view_count,
         t.id AS teacherId,
         t.NAME AS teacherName,
         t.intro,
         t.avatar,
         cd.description,
         s1.id AS subjectLevelOneId,
         s1.title AS subjectLevelOne,
         s2.id AS subjectlevelTwoId,
         s2.title AS subjectlevelTwo
     FROM
         edu_course c
         LEFT OUTER JOIN edu_teacher t ON c.teacher_id = t.id
         LEFT OUTER JOIN edu_course_description cd ON c.id = cd.id
         LEFT OUTER JOIN edu_subject s1 ON c.subject_parent_id = s1.id
         LEFT OUTER JOIN edu_subject s2 ON c.subject_id = s2.id
     WHERE
         c.id = 18
 *
 * @author zhaoyanan
 * @create 2020-02-15-14:08
 */
@Data
public class CourseItemsAllDto {

    //课程表
    private String id; //courseId
    private String title;
    private String price;
    private String cover;
    private String lessonNum;
    private String buyCount;
    private String viewCount;

    //讲师表
    private String teacherId;
    private String teacherName;
    private String intro;
    private String avatar;

    //课程描述表
    private String description;

    //课程分类表
    private String subjectLevelOneId;  //课程一级分类id
    private String subjectLevelOne;  //课程一级分类
    private String subjectlevelTwoId;  //课程一级分类id
    private String subjectlevelTwo; //课程二级分类
}
