package com.chings.core.controller;

import com.chings.core.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Administrator
 * @Date 2018/6/22
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    IUserService userService;

    @ApiOperation(value="登陆", notes="登陆接口,支持ajax和表单提交两种登陆方式")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @RequestMapping(value = "/doLogin", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void doLogin(String name, String password){
        //空接口，不用做处理
    }

    @ApiOperation(value = "登陆页",notes = "用户登录页面")
//    @GetMapping("/login")
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public String login(){

        return "/login";

    }

}
