package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "course")
public class Course {
    private String courseno;
    private String classname;
    private Integer hour;
    private Integer credit;
}
