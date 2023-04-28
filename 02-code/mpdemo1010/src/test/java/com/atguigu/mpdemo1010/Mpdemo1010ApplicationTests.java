package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    //查询user表中的全部数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("岳不群");
        user.setAge(70);
        user.setEmail("ybq@qq.com");
        //最原始方式加创建时间和更新时间,手动设置
        //user.setCreateTime(new Date());
        //user.setUpdateTime(new Date());

        //使用自动填充方式
        int insert = userMapper.insert(user);
        System.out.println("insert操作===========" + insert);
    }

    //修改操作
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1651943150163501058L);
        user.setAge(33);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁(先查再改，才能测出来)
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1651950232665272322L);
        //进行修改
        user.setAge(1000);
        userMapper.updateById(user);
    }

    //多个id的批量查询
    @Test
    public void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage(){
        //1.创建page对象
        //传入2个参数：当前页和每页显示记录数
        Page<User> page = new Page<>(1,3);
        //2.调用mp中分页查询的方法
        //调用mp分页查询的过程中，底层封装
        //把底层所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        //IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        //mapIPage.getRecords().forEach(System.out::println);

        //3.通过page对象获取分页数据
        System.out.println("打印------" + page.getCurrent());   //当前页
        System.out.println(page.getRecords());   //每页数据list集合
        System.out.println(page.getSize());      //每页显示记录数
        System.out.println(page.getTotal());     //总记录数
        System.out.println(page.getPages());     //总页数
        System.out.println(page.hasNext());      //是否有下一页
        System.out.println(page.hasPrevious());  //是否有上一页
    }

    //删除操作 物理删除
    @Test
    public void testDeleteById(){

        int result = userMapper.deleteById(1651960958452682754L);
        System.out.println(result);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(3,4));
        System.out.println(result);
    }
}
