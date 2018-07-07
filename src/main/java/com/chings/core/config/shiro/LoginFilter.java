package com.chings.core.config.shiro;

import com.chings.core.common.Constant;
import com.chings.core.common.bean.Result;
import com.chings.core.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 判断用户是否登录的请求过滤器
 * @Author Administrator
 * @Date 2018/6/26
 */
@Slf4j
public class LoginFilter extends AccessControlFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //因为登陆请求都配置为不拦截了，所以进入此方法的都不是登陆请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //是否登录请求
        boolean ajaxRequest = HttpUtils.jsAjax(httpServletRequest);


        if (ajaxRequest) {
            responseJson(httpServletResponse, Result.create(-1, "用户未登录"));
            return false;
        }

        saveRequestAndRedirectToLogin(request, response);
        return false;

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        Object _user = subject.getSession().getAttribute(Constant.LOGIN_USER_SESSION_KEY);

        if(_user==null){
            return false;
        }

        return true;
    }

    protected void responseJson(HttpServletResponse response,Result result) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(result.toJSONString());
    }

}
