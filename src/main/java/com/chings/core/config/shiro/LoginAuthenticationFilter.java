package com.chings.core.config.shiro;

import com.chings.core.common.exception.NotLoginException;
import com.chings.core.common.Result;
import com.chings.core.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

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
public class LoginAuthenticationFilter extends AuthenticatingFilter {

    private final String DEFAULT_USERNAME_PARAM = "username";
    private final String DEFAULT_PASSWORD_PARAM = "password";

    private final int LOGIN_SUCCESS_CODE = 1;
    private final String LOGIN_SUCCESS_INGO = "登陆成功！";

    private final int LOGIN_FAILED_CODE = -1;
    private final String LOGIN_FAILED_INGO = "登陆失败！";

    private final String LOGIN_SESSION_KEY = "current_user_";

    private String[] loginAjaxUrls;


    public LoginAuthenticationFilter(String[] loginAjaxUrls,String loginView) {
        this.loginAjaxUrls = loginAjaxUrls;
        setLoginUrl(loginView);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String username = getUsername(request);
        String password = getPassword(request);
        return createToken(username, password, request, response);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }

        Subject subject = getSubject(request, response);
        subject.login(token);

        return onLoginSuccess(token, subject, request, response);

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //是否登录请求
        boolean loginRequest = isLoginRequest(request, response);
        //是否ajax请求
        boolean ajaxRequest = HttpUtils.jsAjax(httpServletRequest);

        if(!loginRequest && ajaxRequest){
            return onAjaxLoginFailed((HttpServletRequest) request,(HttpServletResponse) response,new NotLoginException());
        }

        if(!loginRequest && !ajaxRequest){
            return onLoginFailed(request,response,new NotLoginException());
        }

        try {
            executeLogin(request, response);
        } catch (Exception e){
            if(ajaxRequest) return onAjaxLoginFailed(httpServletRequest,httpServletResponse,e);
            onLoginFailed(httpServletRequest,httpServletResponse,e);
        }

        if(ajaxRequest) return onAjaxLoginSuccess(httpServletRequest,httpServletResponse);
        return onLoginSuccess(httpServletRequest,httpServletResponse);

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        Object _user = subject.getSession().getAttribute(LOGIN_SESSION_KEY);

        if(_user==null){
            return false;
        }

        //更换用户名登录，放行
        boolean loginRequest = isLoginRequest(request, response);
        if(loginRequest) return false;

        return true;

    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        subject.getSession().setAttribute(LOGIN_SESSION_KEY,subject.getPrincipal());
        return true;
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        if(pathsMatch(getLoginUrl(),request)){
            return true;
        }
        if( loginAjaxUrls != null && loginAjaxUrls.length > 0 ){
            for (int i=0;i<loginAjaxUrls.length;i++){
                if(pathsMatch(loginAjaxUrls[i], request)) return true;
            }
        }
        return false;

    }

    protected boolean onAjaxLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
        responseJson(response,Result.create(LOGIN_SUCCESS_CODE, LOGIN_SUCCESS_INGO));
        return true;
    }

    protected boolean onLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return true;
    }

    protected boolean onLoginFailed(ServletRequest request, ServletResponse response,Exception e) throws Exception{
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

    protected boolean onAjaxLoginFailed(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception{
        //TODO 细分错误类型
        Result result = null;
        if(e instanceof NotLoginException){
            NotLoginException e1 = (NotLoginException)e;
            result = Result.create(e1.getCode(), e1.getMsg());
        }else{
            result = Result.create(LOGIN_FAILED_CODE, LOGIN_FAILED_INGO);
        }
        responseJson(response,result);
        return false;
    }

    protected String getUsername(ServletRequest request) {
        return WebUtils.getCleanParam(request, DEFAULT_USERNAME_PARAM);
    }

    protected String getPassword(ServletRequest request){
        return WebUtils.getCleanParam(request, DEFAULT_PASSWORD_PARAM);
    }

    protected void responseJson(HttpServletResponse response,Result result) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(result.toJSONString());
    }


}
