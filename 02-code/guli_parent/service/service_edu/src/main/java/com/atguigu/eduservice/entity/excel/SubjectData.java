package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author liming
 * @Date 2023/5/15 17:19
 **/

@Data
public class SubjectData {
    @ExcelProperty(value="一级分类",index = 0)
    private String oneSubjectName;

    @ExcelProperty(value="二级分类",index = 1)
    private String twoSubjectName;
}
