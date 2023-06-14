package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author liming
 * @Date 2023/6/14 15:14
 **/

@Service
public class MsmServiceImpl implements MsmService {
    //发送短信的方法
    @Override
    public boolean send(String phone,String templateCode, Map<String,Object> param) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5tPLDzdtVxBcfpmSmxa1", "8ItKn7czf33rkEwrjitGMIwlsxWyJu");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone);     //手机号
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");   //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode", "SMS_461450417");  //申请阿里云  模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));   //验证码数据，需转换成json数据传递

        //最终发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
