package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.commen.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.python.antlr.ast.Str;
import org.python.modules._systemrestart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * function:  provide interface and return data sets
 */
@RestController
@CrossOrigin
public class WebController {

    @Resource
    UserService userService;

    @RequestMapping("/")
    public Result hello() {
        return Result.success("success");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StrUtil.isBlank(user.getAccount()) || StrUtil.isBlank(user.getPasswords())) {
            return Result.error("false");
        }
        return Result.success(userService.login(user));
    }
    @PostMapping("/rubbish")
    public Result rubbish() {
        List<String> rubbish = userService.rubbish();
        return Result.success(rubbish);
    }
    @PostMapping("/recycle")
    public Result recycle(){
        List<String> recycle = userService.recycle();
        return Result.success(recycle);
    }
    @PostMapping("/harm")
    public Result harm(){
        List<String> harm = userService.harm();
        return Result.success(harm);
    }
    @PostMapping("/kitchen")
    public Result kitchen(){
        List<String> kitchen = userService.kitchen();
        return Result.success(kitchen);
    }
    @PostMapping("/other")
    public Result other(){
        List<String> other = userService.other();
        return Result.success(other);
    }
}
