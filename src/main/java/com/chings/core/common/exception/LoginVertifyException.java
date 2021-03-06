package com.chings.core.common.exception;

import com.chings.core.common.annotation.ErrorCode;
import com.chings.core.common.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @Author Administrator
 * @Date 2018/7/1
 */
@Slf4j
public class LoginVertifyException extends AuthenticationException {

    private String msg;

    private int code;

    public void init(){
        ErrorCode status = this.getClass().getAnnotation(ErrorCode.class);
        if(status==null){
            log.error("ErrorStatus注解没有获取到相应的值"+this.getClass().getName());
            setCode(ErrorEnum.SYSTEM_EXCEPTION.getCode());
            setMsg(ErrorEnum.SYSTEM_EXCEPTION.getMsg());
        } else {
            setCode(status.value().getCode());
            setMsg(status.value().getMsg());
        }
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
