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
    public ResponseBean login(String username, String passWord) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("account",username).eq("password",passWord);
        User user = userMapper.selectOne(queryWrapper);
        ResponseBean responseBean = new ResponseBean();
        if (user!=null){
            responseBean.setCode(ResponseCode.SUCCESS).setData(user);
            return responseBean;
        } else{
            responseBean.setCode(ResponseCode.FAILED).setMessage("account or password error");
            return responseBean;
        }
    }

    @Override
    public ResponseBean register(User user) {
        int result = userMapper.insert(user);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(ResponseCode.SUCCESS).setMessage("insert success");

//        if (result==1){
//
//        }else{
//
//        }
        return responseBean;
    }
}
