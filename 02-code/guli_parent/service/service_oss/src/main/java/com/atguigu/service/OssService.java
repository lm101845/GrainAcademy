package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author liming
 * @Date 2023/5/14 12:18
 **/
public interface OssService{
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
