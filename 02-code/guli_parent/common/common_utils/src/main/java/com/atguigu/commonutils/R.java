package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liming
 * @Date 2023/5/1 22:39
 **/
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 这行代码定义了一个Java类中的私有成员变量 data，类型为Map<String, Object>，初始值为一个空的HashMap<String, Object>。
     * 其中，Map是Java中的映射类型，可以将一个键（key）映射到一个值（value），而HashMap是Map接口的一种实现方式，
     * 可以快速地进行键值对的插入、查找和删除操作。因此，这行代码的意思是在该Java类中定义了一个私有的、用于存储数据的Map容器，
     * 可以用来存储各种类型的数据。
     */

    private R(){}
    //构造函数私有化，不允许直接new(但是在类内部是可以new的)

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
