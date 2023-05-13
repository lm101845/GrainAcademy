/**
 * @Author liming
 * @Date 2023/5/13 15:17
 **/

import request from '@/utils/request'

export default {
  // 1.讲师列表(条件查询分页)
  // current: 当前页  limit: 每页记录数  teacherQuery: 条件对象
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      // url拼接的多种方式,用模板字符串比较好
      // url: '/eduservice/teacher/pageTeacherCondition/' + current + '/' + limit,
      url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      data: teacherQuery
      // teacherQuery条件对象，后端使用requestBody获取数据
      // data表示把对象转换成json进行传递到接口中去
    })
  },
  //2.删除讲师
  deleteTeacherId(id){
    return request({
      url: `/eduservice/teacher/${id}`,
      method: 'delete'
    })
  },
  //3.添加讲师
  addTeacher(teacher){
    return request({
      url: `/eduservice/teacher/addTeacher`,
      method: 'post',
      data: teacher
    })
  },
  //4.根据id查询讲师信息
  getTeacherInfo(id){
    return request({
      url: `/eduservice/teacher/getTeacher/${id}`,
      method: 'get',
    })
  },
  //5.修改讲师
  updateTeacherInfo(teacher){
    return request({
      url: `/eduservice/teacher/updateTeacher`,
      method: 'post',
      data:teacher
    })
  }
}
