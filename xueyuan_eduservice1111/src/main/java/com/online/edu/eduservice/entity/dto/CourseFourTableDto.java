package com.online.edu.eduservice.entity.dto;

import lombok.Data;

/**
 * 用于封装课程详细信息的实体类(涉及四张表)
 *
     SELECT
     c.id,
     c.title,
     c.price,
     c.cover,
     cd.description,
     t.name teacherName,
     s1.title levelOne,
     s2.title levelTwo
     FROM
     edu_course c
     LEFT OUTER JOIN edu_course_description cd ON c.id = cd.id
     LEFT OUTER JOIN edu_teacher t ON c.teacher_id = t.id
     LEFT OUTER JOIN edu_subject s1 ON c.subject_parent_id = s1.id
     LEFT OUTER JOIN edu_subject s2 ON c.subject_id = s2.id
 *
 * @author zhaoyanan
 * @create 2020-02-15-14:08
 */
@Data
public class CourseFourTableDto {

    private String id; //courseId
    private String title;
    private String cover;
    private String price;
    private String description;
    private String teacherName; //课程讲师
    private String levelOne;  //课程一级分类
    private String levelTwo; //课程二级分类
}
