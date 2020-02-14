package com.online.edu.eduservice.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//用于表示章节dto
@Data
public class EduChapterDto {

    private String id;
    private String title;

    private List<EduVideoDto> children = new ArrayList<>();
}
