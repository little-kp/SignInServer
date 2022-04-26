package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class User {

    private String account;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String accounttype;

}


