package com.chings.core.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author Administrator
 * @Date 2018/7/1
 */
public class RandomCodeUsernamePassworToken extends UsernamePasswordToken {

    private String ramdomCode;

    private String sessionId;

    public RandomCodeUsernamePassworToken(String username, String password, String ramdomCode,String sessionId) {
        super(username, password);
        this.ramdomCode = ramdomCode;
        this.sessionId = sessionId;
    }

    public String getRamdomCode() {
        return ramdomCode;
    }

    public String getSessionId() {
        return sessionId;
    }
}
