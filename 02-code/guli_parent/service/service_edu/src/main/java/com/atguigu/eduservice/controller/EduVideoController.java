package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    //1.添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //2.删除小节
    //后面这个方法需要完善(删除小节的时候，同时把里面的视频也删除掉)——已完善
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id得到视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)){
            //先根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);//这个参数是视频id,不是小节id
            if(result.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }

        }
        //【后】删除小节
        videoService.removeById(id);
        return R.ok();
    }

    //3.修改小节  注：自己写的代码
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }
}

