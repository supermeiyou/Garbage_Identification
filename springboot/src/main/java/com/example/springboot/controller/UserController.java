package com.example.springboot.controller;

import com.example.springboot.commen.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return Result.error(false);
        }
        return Result.success(true);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e){
            return Result.error(false);
        }
        return Result.success(true);
    }

    @DeleteMapping("/delete/{account}")
    public Result delete(@PathVariable String account) {
        userService.deleteUser(account);
        return Result.success();
    }

    @GetMapping("/select")
    public Result select(@RequestBody User user) {
        User res = userService.selectUser(user.getAccount(),user.getPasswords());
        return Result.success(res);
    }
}
