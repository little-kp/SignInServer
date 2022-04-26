package com.xk.signinserver.entity.response;

public enum ResponseCode {
    SUCCESS(200,"success"),
    FAILED(400,"");

    ResponseCode(int code,String msg) {
        this.code=code;
        this.msg=msg;
    }

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
}
