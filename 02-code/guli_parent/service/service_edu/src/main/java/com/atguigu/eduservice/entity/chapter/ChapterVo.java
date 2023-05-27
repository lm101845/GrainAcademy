package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liming
 * @Date 2023/5/23 15:12
 **/

@Data
public class ChapterVo {
    private String id;
    private String title;
    //表示小结
    private List<VideoVo> children = new ArrayList<>();
}
