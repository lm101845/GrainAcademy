package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-05-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有一级分类 parentid=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2.查询所有二级分类 parentid != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);   //ne表示不等于
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装的数据
        ArrayList<OneSubject> finalSubjectList = new ArrayList<>();


        //3.封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每一个一级分类对象值
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //得到oneSubjectList中每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            //把eduSubject里面的值获取出来，放到oneSubject对象里面，再把多个oneSubject放到finalSubjectList里面
            OneSubject oneSubject = new OneSubject();
            //写法1：
            //oneSubject.setId(eduSubject.getId());
            //oneSubject.setTitle(eduSubject.getTitle());

            //写法2：
            BeanUtils.copyProperties(eduSubject,oneSubject);
            //这个方法会把eduSubject中的值get出来，然后set到oneSubject中去

            finalSubjectList.add(oneSubject);

            //在一级分类循环里面，遍历查询所有的二级分类
            //创建list集合，封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合
            for (int j = 0; j < twoSubjectList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类parentid和一级分类id是否一样
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubject值复制到twoSubject里面，放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }

        //4.封装二级分类
        return finalSubjectList;
    }
}
