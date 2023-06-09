package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-06-14
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //1.登录的方法
    String login(UcenterMember member);
    //2.注册的方法
    void register(RegisterVo registerVo);

    //3.根据openid做查询
    UcenterMember getOpenIdMember(String openid);

    //4.查询某一天的注册人数
    Integer countRegisterDay(String day);
}
