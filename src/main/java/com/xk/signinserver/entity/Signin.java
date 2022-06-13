package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "signin")
public class Signin {

    @TableId()
    private Integer id;
    private String courseno;
    private String classname;
    private String classno;
    private String teacherno;
    private String account;
    private Long datetime;
}
