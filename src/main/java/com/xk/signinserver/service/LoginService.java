package com.xk.signinserver.service;

import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;

public interface LoginService {
    ResponseBean login(String username, String passWord);
    ResponseBean register(User user);
}
