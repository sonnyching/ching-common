package com.chings.core.controller;

import com.chings.core.common.Constant;
import com.chings.core.common.bean.OnceToken;
import com.chings.core.common.bean.Result;
import com.chings.core.config.shiro.RandomCodeUsernamePassworToken;
import com.chings.core.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    IUserService userService;

    @ApiOperation(value="表单登陆", notes="仅允许表单登陆")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @PostMapping("/doLogin")
    public String doLogin(String username, String password,String randomCode, HttpServletRequest request){

        RandomCodeUsernamePassworToken token = new RandomCodeUsernamePassworToken(username,password,randomCode,request.getSession().getId());
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //TODO 根据错误类型做判断
//            e.printStackTrace();
            return "redirect:/login";
        }

        subject.getSession().setAttribute(Constant.LOGIN_USER_SESSION_KEY,subject.getPrincipal());

        SavedRequest savedRequest = WebUtils.getSavedRequest(request);

        if(savedRequest!=null){
            return "redirect:"+savedRequest.getRequestURI();
        }

        return "redirect:/account";

    }

    @ApiOperation(value="ajax登陆", notes="仅允许ajax post 方式登陆")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @RequestMapping(value = "/ajaxLogin", method = {RequestMethod.POST})
    @ResponseBody
    public Result ajaxLogin(String username, String password){

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //TODO 根据错误类型做判断
            return Result.create(-1,"用户名或密码不正确");
        }

        subject.getSession().setAttribute(Constant.LOGIN_USER_SESSION_KEY,subject.getPrincipal());

        return Result.create(1,"登陆成功！");

    }

    @ApiOperation(value = "登陆页",notes = "用户登录页面")
//    @GetMapping("/login")
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request){

        ModelAndView view = new ModelAndView("/login");

        String vertifyCode = userService.getAndSaveVertifyCode(request.getSession().getId());

        view.addObject("randomCode",vertifyCode);

        return view;

    }

}
