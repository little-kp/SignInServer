package com.xk.signinserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xk.signinserver.entity.CreateSignin;
import com.xk.signinserver.entity.Signin;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.mapper.CreateSigninMapper;
import com.xk.signinserver.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @RequestMapping("/createsignin")
    public ResponseBean createSignIn(@RequestBody CreateSignin signin){
        ResponseBean signInResponse = signInService.createSignIn(signin);
        return signInResponse;
    }

    @RequestMapping("/closesignin")
    public ResponseBean closeSignIn(@RequestBody CreateSignin signin){
        ResponseBean signInResponse = signInService.closeSignIn(signin);
        return signInResponse;
    }

    @RequestMapping("/signin")
    public ResponseBean signin(@RequestBody Signin signin){
        ResponseBean responseBean = signInService.signIn(signin);
        return responseBean;
    }

    @RequestMapping("/checksignstate")
    public ResponseBean checkSigninState(@RequestParam String account){
        ResponseBean responseBean = signInService.checkSigninState(account);
        return responseBean;
    }

    @RequestMapping("/reissueSign")
    public ResponseBean reissueSign(@RequestBody Signin signin){
        System.out.println(signin);
        ResponseBean responseBean = signInService.reissueSign(signin);
        return responseBean;
    }


    @Autowired
    private CreateSigninMapper mapper;

    @RequestMapping("/insert")
    public List<CreateSignin> test(){
//        CreateSignin createSignin = new CreateSignin();
//        createSignin.setAccount("test");
//        createSignin.setClassno("test");
//        createSignin.setCourseno("test");
////        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String format = df.format(new Date());
//        createSignin.setActiontime(new Date());
//        createSignin.setEndtime(new Date());
//        mapper.insert(createSignin);
        List<CreateSignin> createSignins = mapper.selectList(new QueryWrapper<CreateSignin>());
        return createSignins;
    }



}
