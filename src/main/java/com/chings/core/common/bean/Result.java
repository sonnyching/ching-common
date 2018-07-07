package com.chings.core.common.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 请求结果返回类
 * Created by Administrator on 2018/6/21.
 */
public class Result<T> extends JSONObject {

    private static final long serialVersionUID = 1L;
    
    private final String ERROR_KEY = "code";
    private final String MSG_KEY = "msg";
    private final String DATA_KEY = "data";

    public Result() {
        setCode(0);
        setMsg("操作成功");
    }

    public static Result create(int code,Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(JSONObject.toJSONString(object));
        return result;
    }

    public Result addData(T data){
        put(DATA_KEY,data);
        return this;
    }

    public void setCode(int code){
        put(ERROR_KEY,code);
    }

    public void setMsg(String msg){
        put(MSG_KEY,msg);
    }

    public static Result error() {
        return error(1, "操作失败");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.setMsg(msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public String toJSONString(){
        return JSONObject.toJSONString(this);
    }

}
