package com.kevin.instant.core.response;

import com.kevin.instant.core.enums.RespCode;
import com.kevin.instant.util.DateUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Instant
 * @create: 2020-04-20 17:45
 * @author: WYF
 * @description:
 * @version: 1.0
 **/


public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功返回true，失败返回false，除code是200外其他都是false
     */
    private boolean Success;
    /**
     * 枚举RetCode中的code，200表示成功，其他表示失败
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回时间格式精确到毫秒
     */
    private String time;
    /**
     * 返回的数据
     */
    private Object data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(RespCode respCode) {
        this.code = respCode.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response() {
        this.Success = true;
        this.setCode(RespCode.SUCCESS);
        this.msg = "操作成功";
        this.time = DateUtil.getDefaultDateTime();
    }

    public Response(boolean success, Integer code, String msg, String time, Object data) {
        Success = success;
        this.code = code;
        this.msg = msg;
        this.time = time;
        this.data = data;
    }

    public static Response getSuccessResult() {
        Response response = new Response();
        return response;
    }

    public static Response getSuccessResult(String msg) {
        Response response = new Response();
        response.setMsg(msg);
        return response;
    }

    public static Response getSuccessResult(Object data) {
        Response response = new Response();
        response.setData(data);
        return response;
    }

    public static Response getSuccessResult(String msg, Object data) {
        Response response = new Response();
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static Response getErrorResult() {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg("操作失败");
        response.setCode(RespCode.FAIL);
        return response;
    }

    public static Response getErrorResult(String msg) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg(msg);
        response.setCode(RespCode.FAIL);
        return response;
    }

    public static Response getErrorResult(RespCode respCode) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg("操作失败");
        response.setCode(respCode);
        return response;
    }

    public static Response getErrorResult(String msg, RespCode respCode) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg(msg);
        response.setCode(respCode);
        return response;
    }

    public static Response getErrorResult(String msg, Integer code) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMsg(msg);
        response.code = code;
        return response;
    }

    public static Response getErrorResult(String msg, RespCode respCode, Object data) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(respCode);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static Response getErrorResult(String msg, Integer code, Object data) {
        Response response = new Response();
        response.setSuccess(false);
        response.code = code;
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    /**
     * JsonResult转为map
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", this.Success);
        result.put("code", this.code);
        result.put("msg", this.msg);
        result.put("times", this.time);
        result.put("data", this.data);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "Success=" + Success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", data=" + data +
                '}';
    }
}
