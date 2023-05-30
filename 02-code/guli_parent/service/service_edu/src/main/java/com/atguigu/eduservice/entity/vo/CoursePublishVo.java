package com.atguigu.eduservice.entity.vo;

/**
 * @Author liming
 * @Date 2023/5/30 14:20
 **/
import lombok.Data;

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}