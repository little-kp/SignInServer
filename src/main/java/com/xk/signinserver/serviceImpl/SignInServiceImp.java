package com.xk.signinserver.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xk.signinserver.entity.CreateSignin;
import com.xk.signinserver.entity.Signin;
import com.xk.signinserver.entity.StudentCourse;
import com.xk.signinserver.entity.User;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.mapper.CreateSigninMapper;
import com.xk.signinserver.mapper.SigninMapper;
import com.xk.signinserver.mapper.StudentCourseMapper;
import com.xk.signinserver.mapper.UserMapper;
import com.xk.signinserver.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SignInServiceImp implements SignInService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CreateSigninMapper createSignInMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private SigninMapper signinMapper;

    private StudentCourse studentCourse;
    @Override
    public ResponseBean createSignIn(CreateSignin signinInfo) {
        int result = createSignInMapper.insert(signinInfo);
        ResponseBean responseBean = new ResponseBean();
        if (result>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("创建考勤成功");
            studentCourse = studentCourseMapper.selectOne(new QueryWrapper<StudentCourse>().select().eq("account", signinInfo.getAccount()).eq("classno", signinInfo.getClassno()));
            studentCourseMapper.update(studentCourse,new UpdateWrapper<StudentCourse>().set("signinstate",1));
        } else{
            responseBean.setCode(ResponseCode.FAILED).setMessage("create signIn failed,please try to reCreate");
        }
        return responseBean;
    }

    @Override
    public ResponseBean closeSignIn(CreateSignin signinInfo) {
        if(signinInfo != null){
            studentCourseMapper.update(studentCourse,new UpdateWrapper<StudentCourse>().set("signinstate",0));
        }
//        StudentCourse studentCourse = studentCourseMapper.selectOne(new QueryWrapper<StudentCourse>().select().eq("account", signinInfo.getAccount()).eq("classno", signinInfo.getClassno()));
//        studentCourseMapper.update(new UpdateWrapper<StudentCourse>().set("signinstate"));
        long time = new Date().getTime();
        CreateSignin createSignin = createSignInMapper.selectOne(new QueryWrapper<CreateSignin>().select().eq("actiontime", signinInfo.getActiontime()).eq("account", signinInfo.getAccount()));
        createSignin.setEndtime(time);
        createSignInMapper.updateById(createSignin);
        Long actiontime = signinInfo.getActiontime();
        Long endtime = time;
        //本次已打卡用户数据
        List<Signin> signinList = signinMapper.selectList(new QueryWrapper<Signin>().select().ge("datetime", actiontime).le("datetime", endtime));
        System.out.println(signinList);
        //查询班级内所有用户
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().select().eq("class", signinInfo.getClassno()));
        List<Signin> list = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            Signin sign = null;
            for (Signin signin : signinList) {
                sign = null;
                if (signin.getAccount().equals(user.getAccount())){
                    list.add(signin);
                }else {
                    sign = new Signin();
                    sign.setAccount(user.getAccount());
                    sign.setClassname(signin.getClassname());
                    sign.setClassno(signin.getClassno());
                    sign.setCourseno(signin.getCourseno());
                    sign.setTeacherno(signin.getTeacherno());
                    sign.setDatetime(0L);
                    list.add(sign);
                }
                if (sign!=null){
                    signinMapper.insert(sign);
                }
            }
        }
        ResponseBean responseBean = new ResponseBean();
        if(signinList.size()>0){
            responseBean.setCode(ResponseCode.SUCCESS).setData(list);
        }
        return responseBean;
    }

    @Override
    public ResponseBean signIn(Signin signin) {
        int insert = signinMapper.insert(signin);
        ResponseBean responseBean = new ResponseBean();
        if (insert>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("考勤成功");
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("考勤失败");
        }
        return responseBean;
    }

    @Override
    public ResponseBean checkSigninState(String account) {
        ResponseBean responseBean = null;
        QueryWrapper<StudentCourse> queryWrapper = new QueryWrapper<>();
        String classno = account.substring(0, account.length() - 2);
        queryWrapper.select().eq("classno",classno);
        List<StudentCourse> studentCourses = studentCourseMapper.selectList(queryWrapper);
        for (StudentCourse studentCours : studentCourses) {
            if (studentCours.getSigninstate()==1){
                 responseBean = new ResponseBean().setCode(ResponseCode.SUCCESS).setMessage("sigin exist").setData(studentCours);
            }
        }
        if (responseBean==null){
            responseBean = new ResponseBean().setCode(ResponseCode.FAILED).setMessage("没有正在进行的考勤");
        }
        return responseBean;
    }

    @Override
    public ResponseBean reissueSign(Signin signin) {
        ResponseBean responseBean = new ResponseBean();
        System.out.println(signin+"----");
        signin.setDatetime(new Date().getTime());
        int datetime = signinMapper.updateById(signin);
//        int datetime = signinMapper.update(signin, new UpdateWrapper<Signin>().eq("account",signin.getAccount()).eq("datetime",signin.getDatetime()).set("datetime", new Date().getTime()));
        if (datetime>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("补卡成功");
        }else{
            responseBean.setCode(ResponseCode.FAILED).setMessage("补卡失败");

        }
        return responseBean;
    }


}
