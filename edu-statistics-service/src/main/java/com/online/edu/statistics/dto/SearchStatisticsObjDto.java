package com.online.edu.statistics.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author NanCoder
 * @create 2020-03-25-10:19
 */
@Data
public class SearchStatisticsObjDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;//统计因子
    private String begin;//查询范围，开始时间
    private String end; //查询范围，结束时间

}
