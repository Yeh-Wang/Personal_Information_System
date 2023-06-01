package com.yehwang.design.config;

/**
 * @author :Yaxin-Wang
 * @date : 2022/12/19
 */
public class ResultConfig<T> {
    private String code;
    private  String msg;
    private  T entity;
    public ResultConfig(){}
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return entity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T entity) {
        this.entity = entity;
    }

}
