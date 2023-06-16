package com.atguigu.educenter.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.educenter.utils.ConstantWxUtils;
import com.atguigu.educenter.utils.HttpClientUtils;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author liming
 * @Date 2023/6/16 14:40
 **/

@CrossOrigin
@Controller//注意这里没有配置 @RestController,因为只是请求地址，不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;

    //2.获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code,String state){
      try{
          System.out.println(code + "=====code");
          System.out.println(state + "=====state");

          //1.获取code值，临时票据，类似于验证码
          //2.拿着code去请求微信固定的地址，得到2个值 access_token和openid
          String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                  "?appid=%s" +
                  "&secret=%s" +
                  "&code=%s" +
                  "&grant_type=authorization_code";
          //拼接3个参数：id 密钥 code值
          //设置%s里面值
          String accessTokenUrl = String.format(
                  baseAccessTokenUrl,
                  ConstantWxUtils.WX_OPEN_APP_ID,
                  ConstantWxUtils.WX_OPEN_APP_SECRET,
                  code);

          //请求这个拼接好的地址，得到返回的2个值： access_token和openid
          //使用httpclient发送请求，得到返回结果
          String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
          System.out.println("accessTokenInfo=================" + accessTokenInfo);
          //把accessTokenInfo字符串获取出来2个值 access_token和openid
          //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
          //使用json转换工具 Gson
          Gson gson = new Gson();
          HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
          String  accessToken = (String) mapAccessToken.get("access_token");
          String openid = (String) mapAccessToken.get("openid");

          //把扫码人信息添加到数据库中去
          //首先判断数据库表里面是否存在相同微信信息(根据openid判断)
          UcenterMember member = memberService.getOpenIdMember(openid);
          if (member == null){   //member是空，表示表里面没有相同微信数据，进行添加
              //3.拿着得到的access_token和openid再去请求微信提供的固定的地址，获取到扫码人的信息
              //访问微信的资源服务器，获取用户信息
              String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                      "?access_token=%s" +
                      "&openid=%s";
              String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);

              String userInfo = HttpClientUtils.get(userInfoUrl);
              System.out.println("userInfo==========" + userInfo);
              //获取返回userInfo字符串扫描人信息
              HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
              String nickname = (String) userInfoMap.get("nickname");      //昵称
              String headimgurl = (String) userInfoMap.get("headimgurl");  //头像

              member = new UcenterMember();
              member.setOpenid(openid);
              member.setNickname(nickname);
              member.setAvatar(headimgurl);
              memberService.save(member);
          }
          //使用jwt根据member对象生成token字符串
          String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
          //最后，返回首页面,通过路径传递token字符串
          return "redirect:http://localhost:3000?token=" + jwtToken;
      }catch (Exception e){
          throw new GuliException(20001, "登录失败");
      }

    }

    //1.生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode(){
        //方法1：固定地址，后面拼接参数
        //String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ConstantWxUtils.WX_OPEN_APP_ID + "&response_type=code";
        //方法2：
        // 微信开放平台授权baseUrl %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 对redirect——url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001, e.getMessage());
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu");
        //重定向请求微信地址
        return "redirect:" + url;
    }
}
