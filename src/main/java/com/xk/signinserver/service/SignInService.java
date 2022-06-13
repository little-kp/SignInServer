package com.xk.signinserver.service;

import com.xk.signinserver.entity.CreateSignin;
import com.xk.signinserver.entity.Signin;
import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;

public interface SignInService {
    //发起打卡
    ResponseBean createSignIn(CreateSignin signinInfo);
    //关闭打卡
    ResponseBean closeSignIn(CreateSignin signinInfo);
    //打卡
    ResponseBean signIn(Signin signin);
    //检查打卡状态
    ResponseBean checkSigninState(String account);
    //补卡
    ResponseBean reissueSign(Signin signin);
}
