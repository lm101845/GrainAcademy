package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author liming
 * @Date 2023/6/5 16:36
 **/

@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)  //调用的服务器名称
@Component
public interface VodClient {
    //1.定义调用的方法路径
    //根据视频id删除阿里云视频
    //PathVariable注解一定要指定参数名称，否则出错
    @DeleteMapping(value = "/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    //2.定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
