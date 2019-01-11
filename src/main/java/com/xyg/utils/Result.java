package com.xyg.utils;

/**
 * 全局响应对象
 */
public class Result {
    //状态码
    private int status;
    //描述信息
    private String msg;
    //数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setData(object);
        result.setStatus(200);
        result.setMsg("ok");
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setData(null);
        result.setStatus(200);
        result.setMsg("ok");
        return  result;
    }

    public static Result error(String mes){
        Result result = new Result();
        result.setData(null);
        result.setStatus(405);
        result.setMsg(mes);
        return  result;
    }
}
