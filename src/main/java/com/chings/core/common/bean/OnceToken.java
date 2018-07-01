package com.chings.core.common.bean;

import com.chings.core.common.inter.Token;
import com.chings.core.utils.WebSafetyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 一次性token，存储在session中，用完即失效
 * @Author Administrator
 * @Date 2018/7/1
 */
public class OnceToken implements Token {

    private String key;

    public OnceToken(String saveKey) {
        this.key = saveKey;
    }

    @Override
    public boolean storeToken(String token) {
        SecurityUtils.getSubject().getSession().setAttribute(key,token);
        return true;
    }

    @Override
    public String createToken() {
        return WebSafetyUtil.getRandomToken();
    }

    @Override
    public boolean vertifyToken( String token) {

        String storeToken = (String)SecurityUtils.getSubject().getSession().getAttribute(key);

        if(StringUtils.isEmpty(storeToken)){
            return false;
        }

        if(!token.equals(storeToken)){
            return false;
        }

        SecurityUtils.getSubject().getSession().removeAttribute(key);

        return true;
    }

}
