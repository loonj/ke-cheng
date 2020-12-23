package cn.zhanx.ke.cheng.exception;

import cn.zhanx.ke.cheng.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e){
        log.error("[ 系统异常 ]{}",e.getMessage());
        if(e instanceof CustomException){
            CustomException customException=(CustomException) e;
            return Result.error(customException.getCode().toString(),customException.getMsg());
        }else {
            return Result.error("1005","全局异常，未知错误");
        }
    }
}
