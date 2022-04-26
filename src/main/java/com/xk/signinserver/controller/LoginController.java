package com.xk.signinserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.mapper.UserMapper;
import com.xk.signinserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public ResponseBean login(String number,String password){
        ResponseBean loginResponse = loginService.login(number, password);
//        if (loginResponse.getCode()== ResponseCode.SUCCESS) return loginResponse;
        return loginResponse;
    }

    @RequestMapping("/register")
    public User register(User user){

        return null;
    }


}
