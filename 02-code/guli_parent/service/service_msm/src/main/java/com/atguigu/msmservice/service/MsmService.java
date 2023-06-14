package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @Author liming
 * @Date 2023/6/14 15:13
 **/
public interface MsmService {
    //发送短信的方法
    boolean send(String PhoneNumbers, String templateCode, Map<String,Object> param);
}
