//package com.after00.advice;
//
//import com.after00.common.BaseResponse;
//import com.after00.common.ResponseCodeEnum;
//import com.after00.exception.TestRuntimeException;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * @author wangbiao
// */
//@ControllerAdvice
//@Slf4j
//public class GlobalExceptionHandleAdvice extends ResponseEntityExceptionHandler {
//
//    /**
//     * 处理自定义异常
//     */
//    @ExceptionHandler(TestRuntimeException.class)
//    @ResponseBody
//    BaseResponse handleException(HttpServletRequest request, TestRuntimeException ex) {
//        log.info("++++++++++++++++++==============================");
//        return BaseResponse.getFailResponse(ResponseCodeEnum.fail.key,ex.getMessage(), JSON.toJSONString(request.getParameterMap()));
//    }
//
//}
