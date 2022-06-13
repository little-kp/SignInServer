package com.xk.signinserver.controller;

import com.xk.signinserver.entity.StudentCourse;
import com.xk.signinserver.entity.response.ResponseBean;
import com.xk.signinserver.entity.response.ResponseCode;
import com.xk.signinserver.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SelectController {

    @Autowired
    private SelectService selectService;

    @RequestMapping("/selectsignin")
    public ResponseBean selectSignin(@RequestParam String account){
        System.out.println(account);
        List<StudentCourse> studentCourses = selectService.selectStudentCourse(account);
        ResponseBean responseBean = selectService.selectSigninByAccount(studentCourses,account);
        return responseBean;
    }

    @RequestMapping("/selectStudentCourse")
    public ResponseBean selectStudentCourse(@RequestParam String teacherno){
        System.out.println(teacherno);
        List<StudentCourse> studentCourses = selectService.selectStudentCourse(teacherno);
        ResponseBean responseBean;
        if (studentCourses.size()>0){
            responseBean = new ResponseBean().setCode(ResponseCode.SUCCESS).setMessage("select success").setData(studentCourses);
        }else {
            responseBean = new ResponseBean().setCode(ResponseCode.FAILED).setMessage("select failed");
        }
        return responseBean;
    }

    @RequestMapping("/getIdentity")
    public ResponseBean selectIdentity(@RequestParam String account){
        ResponseBean responseBean = selectService.selectIdentity(account);
        return responseBean;
    }

    @RequestMapping("/selectCoursename")
    public ResponseBean selectCoursename(@RequestParam String courseno){
        ResponseBean responseBean = selectService.selectCoursename(courseno);
        return responseBean;
    }

    @RequestMapping("/selectCreateSign")
    public ResponseBean selectCreateSign(@RequestParam String teacherno){
        ResponseBean responseBean = selectService.selectCreateSign(teacherno);
        return responseBean;
    }

}
