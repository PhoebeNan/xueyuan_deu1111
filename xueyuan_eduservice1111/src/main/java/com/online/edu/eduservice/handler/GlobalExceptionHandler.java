package com.online.edu.eduservice.handler;

import com.online.edu.common.R;
import com.online.edu.eduservice.exception.EduException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author zhaoyanan
 * @create 2020-01-08-15:08
 */
//实现了aop的思想
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 对所有的异常进行统一的处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R globalError(Exception e){
        e.printStackTrace();
        System.out.println("统一异常处理");
        return R.error().message("页面出现异常了");
    }

    /**
     * 对特定的异常进行统一的处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R specificalError(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("0不不能为除数，出现了异常");
    }

    /**
     * 对自定义异常进行统一的处理
     * @param e
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R customizeError(EduException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}
