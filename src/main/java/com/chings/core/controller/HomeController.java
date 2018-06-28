package com.chings.core.controller;

import com.chings.core.common.exception.DBException;
import com.chings.core.common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/12/17.
 */
@Controller
@Slf4j
class HomeController {

    @ApiOperation(value="首页", notes="首页")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String home() {
        return "/home";
    }

    @RequestMapping(value="/home", method= RequestMethod.GET)
    @ResponseBody
    public Result ajaxHome() {
        return Result.ok();
    }

}
