package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author liming
 * @Date 2023/5/13 14:06
 **/
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin
public class EduLoginController {
    //login方法
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info方法
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://avatars.githubusercontent.com/u/57181681?v=4");
    }
}
