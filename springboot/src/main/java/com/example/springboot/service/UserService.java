package com.example.springboot.service;

import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(String account) {
        userMapper.deleteUser(account);
    }

    public User selectUser(String account, String passwords) {
        return  userMapper.selectUser(account,passwords);
    }

    public boolean login(User user) {
        User dbUser = userMapper.selectUser(user.getAccount(),user.getPasswords());
        if(dbUser==null)
            return false;
        else
            return true;

    }

    public List<String> rubbish() {
        return userMapper.rubbish();
    }

    public List<String> recycle() {
        return userMapper.recycle();
    }
    public List<String> harm() {
        return userMapper.harm();
    }
    public List<String> kitchen() {
        return userMapper.kitchen();
    }
    public List<String> other() {
        return userMapper.other();
    }
}
