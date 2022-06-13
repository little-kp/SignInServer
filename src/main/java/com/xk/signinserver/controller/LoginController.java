package com.xk.signinserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.mapper.UserMapper;
import com.xk.signinserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ResponseBean login(@RequestParam String number, @RequestParam String password){
        ResponseBean loginResponse = loginService.login(number, password);
        return loginResponse;
    }

    @RequestMapping("/register")
    public ResponseBean register(@RequestBody User user){
        ResponseBean loginResponse = loginService.register(user);
        return loginResponse;
    }


}
