package com.xk.signinserver.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xk.signinserver.entity.*;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.mapper.*;
import com.xk.signinserver.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelectServiceImp implements SelectService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SigninMapper signinMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private CreateSigninMapper createSigninMapper;
    @Autowired
    private IdentityMapper identityMapper;
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<StudentCourse> selectStudentCourse(String account) {
        QueryWrapper<StudentCourse> queryWrapper = new QueryWrapper<>();
        if (account.startsWith("B")){
            String classno = account.substring(0, account.length() - 2);
            queryWrapper.select().eq("classno",classno);
        }else if(account.startsWith("T")){
            queryWrapper.select().eq("account",account);
        }
        List<StudentCourse> studentCourses = studentCourseMapper.selectList(queryWrapper);
        return studentCourses;
    }

    @Override
    public ResponseBean selectSigninByAccount(List<StudentCourse> list,String account) {
        QueryWrapper<Signin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("account",account).or().eq("teacherno",account);
        List<Signin> signinList = signinMapper.selectList(queryWrapper);
        System.out.println(signinList+"-----------------------");
        Map<String,List<Signin>> signMap = new HashMap<>();
        for (StudentCourse studentCourse : list) {
            String courseno = studentCourse.getCourseno();
            String classno = studentCourse.getClassno();
            Course course = courseMapper.selectOne(new QueryWrapper<Course>().select().eq("courseno", courseno));
            List<Signin> signlist = new ArrayList<>();
            for (int i = 0; i < signinList.size(); i++) {
                Signin signin = signinList.get(i);
                if (signin.getCourseno().equals(courseno)){
                    signlist.add(signin);
                }
            }
            if (account.startsWith("B")){
                signMap.put(course.getClassname(),signlist);
            }else if(account.startsWith("T")){
                signMap.put(classno,signlist);
            }
        }
        System.out.println(signMap);
        ResponseBean responseBean = new ResponseBean();
        if (signMap.size()>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("select success").setData(signMap);
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("data is null");
        }
        return responseBean;
    }

    @Override
    public ResponseBean selectIdentity(String account) {
        ResponseBean responseBean = new ResponseBean();
        Identity identity = identityMapper.selectOne(new QueryWrapper<Identity>().select().eq("account", account));
        if (identity!=null){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("select success").setData(identity);
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("data is null");
        }
        return responseBean;
    }

    @Override
    public ResponseBean selectCoursename(String courseno) {
        ResponseBean responseBean = new ResponseBean();
        Course course = courseMapper.selectOne(new QueryWrapper<Course>().select().eq("courseno", courseno));
        if (course!=null){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("select success").setData(course);
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("data is null");
        }
        return responseBean;
    }

    @Override
    public ResponseBean selectCreateSign(String teacherno) {
        ResponseBean responseBean = new ResponseBean();
        QueryWrapper<StudentCourse> queryWrapper = new QueryWrapper<>();
        Map<String,List<CreateSignin>> creatsignmap = new HashMap<>();
        queryWrapper.select().eq("account",teacherno);
        List<StudentCourse> studentCourses = studentCourseMapper.selectList(queryWrapper);
        List<CreateSignin> list = createSigninMapper.selectList(new QueryWrapper<CreateSignin>().select().eq("account",teacherno));
        for (StudentCourse studentCours : studentCourses) {
            List<CreateSignin> createSigninList = new ArrayList<>();
            for (CreateSignin createSignin : list) {
                if (studentCours.getClassno().equals(createSignin.getClassno())){
                    System.out.println(createSignin);
                    createSigninList.add(createSignin);
                }
            }
            creatsignmap.put(studentCours.getClassno(),createSigninList);
        }
        if (creatsignmap.size()>0){
            responseBean.setCode(ResponseCode.SUCCESS).setMessage("select success").setData(creatsignmap);
        }else {
            responseBean.setCode(ResponseCode.FAILED).setMessage("data is null");
        }
        return responseBean;
    }
}
