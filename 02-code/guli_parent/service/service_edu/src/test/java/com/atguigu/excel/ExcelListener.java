package com.atguigu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author liming
 * @Date 2023/5/15 17:01
 **/
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("******--" + data);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成之后——这里用不上
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
