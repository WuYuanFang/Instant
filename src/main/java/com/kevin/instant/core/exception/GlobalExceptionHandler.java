package com.kevin.instant.core.exception;

import com.kevin.instant.core.enums.RespCode;
import com.kevin.instant.core.response.Response;
import com.kevin.instant.util.JacksonUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Instant
 * @create: 2020-04-21 22:12
 * @author: WYF
 * @description:
 * @version: 1.0
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(request.getRequestURL());

        if (e instanceof NoHandlerFoundException) {
            logger.error("请求url不存在--{}", url);
            return Response.getErrorResult(messageSource.getMessage("not.found", null, "", null), RespCode.NOT_FOUND);
        } else if (e instanceof MethodArgumentNotValidException || e instanceof BindException){
            // 统一validator参数校验
            FieldError fieldError;
            if(e instanceof MethodArgumentNotValidException){
                fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
            } else {// org.springframework.validation.BindException
                fieldError = ((BindException) e).getBindingResult().getFieldError();
            }
            if (fieldError != null) {
                return Response.getErrorResult(fieldError.getDefaultMessage(), RespCode.FAIL);
            } else {
                return Response.getErrorResult("未通过参数校验", RespCode.FAIL);
            }
        } else if (e instanceof MissingServletRequestParameterException) {
            // 使用@RequestParam，required为true时，没传入参数
            MissingServletRequestParameterException me = (MissingServletRequestParameterException) e;
            logger.error("请求url时缺失必要的参数: {}，请求地址: {}", me.getParameterName(), url);
            return Response.getErrorResult("缺失" + me.getParameterName() + "参数", RespCode.FAIL);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            logger.error("请求不存在: {}", url);
            return Response.getErrorResult("请求不存在", RespCode.NOT_FOUND);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException nfe = (MethodArgumentTypeMismatchException) e;
            return Response.getErrorResult(nfe.getName() + "参数值不合法", RespCode.FAIL);
        } else if (e instanceof DKException) {//自定义异常
            DKException be = (DKException) e;
            return Response.getErrorResult(be.getMessage(), RespCode.FAIL);
        } else {
            //其他未处理的异常统一处理
            Map<String, String> map = getRequestParams(request);
            logger.error("GlobalExceptionHandler --- 505 系统出现异常，请求路径：{}" + "\n请求参数：{}", url.toString(), JacksonUtils.toJson(map), e);
            return Response.getErrorResult("系统出现异常，请联系技术人员", RespCode.INTERNAL_SERVER_ERROR, map);
        }
    }

    /**
     * 获取请求参数
     * @param request
     * @return 如果没有请求参数，返回一个empty map
     */
    private Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap==null || parameterMap.size()==0) {
            return Collections.emptyMap();
        }

        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();
            if (paramValues != null && paramValues.length > 0) {
                String paramValue = String.join(",", paramValues);
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

}
