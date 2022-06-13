package com.xk.signinserver.service;

import com.xk.signinserver.entity.Signin;
import com.xk.signinserver.entity.StudentCourse;
import com.xk.signinserver.entity.response.ResponseBean;

import java.util.List;
import java.util.Map;

public interface SelectService {
    //通过学号拿到班级号，通过班级号获取老师授课表里对应的数据（一个班级对应不同的课）
    List<StudentCourse> selectStudentCourse(String account);
    //通过授课表里的课程号和老师账户名去打卡记录表里查询，查出来数据很多，用课程号当作主键，对应的考勤数据则在value的list里存放
    ResponseBean selectSigninByAccount(List<StudentCourse> list,String account);
    //通过账号查询身份
    ResponseBean selectIdentity(String account);
    //通过课号查询课程名
    ResponseBean selectCoursename(String courseno);
    //获取创建打卡数据
    ResponseBean selectCreateSign(String teacherno);

}
