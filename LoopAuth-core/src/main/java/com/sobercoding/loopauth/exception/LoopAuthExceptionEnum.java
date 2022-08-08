package com.sobercoding.loopauth.exception;

/**
 * @program: LoopAuth
 * @author: Sober
 * @Description:
 * @create: 2022/08/08 19:28
 */
public enum LoopAuthExceptionEnum {
    // 未知错误
    ERROR(500,"未知错误"),
    // 账户未登录
    LOGIN_NOT_EXIST(401,"会话不存在");

    /**
     * 异常状态码
     */
    private int code;

    /**
     * 异常消息
     */
    private String msg;

    LoopAuthExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
