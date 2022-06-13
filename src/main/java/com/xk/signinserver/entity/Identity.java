package com.xk.signinserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "identity")
public class Identity {
    private String account;
    private String accountType;
}
