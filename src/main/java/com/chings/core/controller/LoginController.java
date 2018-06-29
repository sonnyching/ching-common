package com.chings.core.controller;

import com.chings.core.common.Result;
import com.chings.core.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String doLogin(String name, String password, HttpServletRequest request){

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
    public Result ajaxLogin(String name, String password){

        return Result.create(1,"登陆成功！");

    }

    @ApiOperation(value = "登陆页",notes = "用户登录页面")
//    @GetMapping("/login")
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public String login(){

        return "/login";

    }

}
