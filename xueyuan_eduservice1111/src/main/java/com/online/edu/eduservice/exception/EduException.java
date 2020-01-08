package com.online.edu.eduservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyanan
 * @create 2020-01-08-15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException {

    private Integer code; //状态码
    private String message; //异常信息

}
