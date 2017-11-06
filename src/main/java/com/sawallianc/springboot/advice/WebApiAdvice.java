package com.sawallianc.springboot.advice;

import com.sawallianc.entity.Result;
import com.sawallianc.entity.ResultCode;
import com.sawallianc.entity.exception.BizRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
public class WebApiAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApiAdvice.class);
    public WebApiAdvice() {
    }

    @ExceptionHandler
    public Object handleExceotion(Exception e){
        Result result = Result.failResult();
        String errMsg;
        if(e instanceof BizRuntimeException){
            ResultCode resultCode = ((BizRuntimeException) e).getResultCode();
            result.setResultCode(resultCode);
            result.setMsg(resultCode.getMsg());
            errMsg = "[error code: "+resultCode.getCode()+"]";
        } else {
            errMsg = "[error code: -1]";
        }
        LOGGER.error(errMsg,e);
        return result;
    }
}
