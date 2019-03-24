package com.example.demo1.common;

/**
 * 状态码的对应关系
 *
 * @auther willi
 * @create-time 2019-03-20-16:07
 */


public class ResultCode {
    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求
    public static final int RESULT_CODE_BAD_REQUEST = 412;  // 请求错误
    public static final int RESULT_CODE_NOT_LOGIN = 402;  // 未登录
    public static final int RESULT_CODE_PARAM_ERROR = 406;  // 传参错误
    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 服务器错误

//    public final static int PAGE_SIZE = 10;//默认分页条数
}
