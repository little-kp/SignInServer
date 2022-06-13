package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName(value = "createsignin")
public class CreateSignin {
    @TableId()
    private Integer id;
    private String account;
    private String courseno;
    private Long actiontime;
    private Long endtime;
    private String classno;
    private String location;
}
