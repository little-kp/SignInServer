package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "studentcourse")
public class StudentCourse {
    private String courseno;
    private String account;
    private String classno;
    private Integer signinstate;
}
