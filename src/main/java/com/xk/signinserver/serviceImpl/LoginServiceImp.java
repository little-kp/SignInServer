package com.xk.signinserver.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.mapper.UserMapper;
import com.xk.signinserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseBean login(String account, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("account",account).eq("password",passWord);
        User user = userMapper.selectOne(queryWrapper);
        ResponseBean responseBean = new ResponseBean();
        if (user!=null){
            responseBean.setCode(ResponseCode.SUCCESS).setData(user);
        } else{
            responseBean.setCode(ResponseCode.FAILED).setMessage("account or password error");
        }
        System.out.println(user);
        return responseBean;
    }

    @Override
    public ResponseBean register(User user) {
        int result = userMapper.insert(user);
        System.out.println(result);
        ResponseBean responseBean = new ResponseBean();
        if (result>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("register success");
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("register failed");
        }
        return responseBean;
    }
}
