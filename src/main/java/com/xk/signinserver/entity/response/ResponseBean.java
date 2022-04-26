package com.xk.signinserver.entity.response;

import java.util.Date;

public class ResponseBean {
    private ResponseCode code;
    private String message;
    private Object data;


    public ResponseBean() {
    }

    public Object getData() {
        return data;
    }

    public ResponseBean setData(Object data) {
        this.data = data;
        return this;
    }

    public ResponseCode getCode() {
        return code;
    }

    public ResponseBean setCode(ResponseCode code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseBean setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
