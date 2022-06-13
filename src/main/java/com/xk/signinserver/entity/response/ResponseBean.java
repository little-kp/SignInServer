package com.xk.signinserver.entity.response;

import java.util.Date;

public class ResponseBean {
    private int code;
    private String message;
    private Object data;


    public ResponseBean(ResponseCode responseCode) {
        code = responseCode.getCode();
        message = responseCode.getMsg();
    }

    public ResponseBean() {
    }

    public Object getData() {
        return data;
    }

    public ResponseBean setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseBean setCode(ResponseCode responseCode) {
        code = responseCode.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBean setMessage(String message) {
        this.message = message;
        return this;
    }

}
