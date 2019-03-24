package com.example.demo1.common;

/**
 * 响应结果生成工具
 *
 * @auther willi
 * @create-time 2019-03-20-15:33
 */


public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";

    public static Result getSucessResult(){
        Result result = new Result();
        result.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result getSucessResult(String msg){
        Result result = new Result();
        result.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        result.setMsg(msg);
        return result;
    }

    public static Result getSucessResult(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static Result getSucessResult(int code, String msg, Object data){
        Result result = new Result();
        result.setResultCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result getFailResult(){
        Result result = new Result();
        result.setResultCode(ResultCode.RESULT_CODE_SERVER_ERROR);
        result.setMsg(DEFAULT_FAIL_MESSAGE);
        return result;
    }

    public static Result getFailResult(String msg){
        Result result = new Result();
        result.setResultCode(ResultCode.RESULT_CODE_BAD_REQUEST);
        result.setMsg(msg);
        return result;
    }

    public static Result getFailResult(int code,String msg){
        Result result = new Result();
        result.setResultCode(code);
        result.setMsg(msg);
        return result;
    }



}
