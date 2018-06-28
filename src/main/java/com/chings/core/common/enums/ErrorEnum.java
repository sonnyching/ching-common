package com.chings.core.common.enums;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by Administrator on 2018/6/22.
 */
public enum ErrorEnum {

    PARAM_EXCEPTION(10000,"参数异常"),
    NET_EXCEPTION(20000,"网络异常"),
    DB_EXCEPTION(30000,"数据库异常"),
    UNKOWN_EXCEPTION(-1,"未知异常"),
    SYSTEM_EXCEPTION(-2,"系统异常异常"),
    NOT_FOUND_EXCEPTION(-3,"未找到相关记录"),
    NOT_LOGIN_EXCEPTION(-4,"用户未登录"),
    NO_PERMMISION_EXCEPTION(-4,"无权限")

    ;

    ;

    private String msg;
    private int code;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
