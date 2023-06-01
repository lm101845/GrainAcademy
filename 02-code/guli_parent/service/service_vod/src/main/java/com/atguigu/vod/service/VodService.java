package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author liming
 * @Date 2023/6/1 22:08
 **/
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
}
