package com.epoch.handler;

import com.epoch.model.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数异常处理
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(Exception e) {
        e.printStackTrace();
        String error1;
        try {
            error1 = e.getMessage().split("default message \\[")[2];
        } catch (Exception ex) {
            return Result.fail("参数异常，请完善表单信息");
        }
        String errorMsg = error1.substring(0, error1.indexOf("]"));
        return Result.fail(errorMsg);
    }

    /**
     * 参数异常处理
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result handleMissingServletRequestParameterException(Exception e) {
        e.printStackTrace();
        return Result.fail("参数异常，请完善表单信息");
    }




    /**
     * 兜底异常处理
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        e.printStackTrace();
        String errorMsg = e.getMessage();
        if (errorMsg==null || errorMsg.length() > 20) {
            e.printStackTrace();
            errorMsg = "系统异常，请重试!";
        }
        return Result.fail(errorMsg);
    }


}