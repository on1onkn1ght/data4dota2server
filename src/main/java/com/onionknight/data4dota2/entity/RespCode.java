package com.onionknight.data4dota2.entity;

/**
 * @Author :fdy
 * @Date :Created in 15:43 2019/1/12
 * @Description:
 * @ModifiedBy :
 * @Version :
 */
public enum RespCode {
    SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试"),
    UNAUTHORIZED(-2,"权限不足");
    private int code;
    private String msg;
    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
