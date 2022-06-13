package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@TableName(value = "user")
public class User {

    @TableId(value = "")
    private String account;
    private String password;
    private String name;
    private String sex;
    private Integer age;

}


