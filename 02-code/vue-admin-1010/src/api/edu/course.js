/**
 * @Author liming
 * @Date 2023/5/22 14:40
 **/

import request from '@/utils/request'

export default {
  // 1.添加课程信息
  addCourseInfo(courseInfo) {
    return request({
      url: '/eduservice/course/addCourseInfo',
      method: 'post',
      data: courseInfo
    })
  },
  //2 查询所有讲师
  getListTeacher() {
    return request({
      url: '/eduservice/teacher/findAll',
      method: 'get'
    })
  },
  //3.根据课程id查询课程基本信息
  getCourseInfoId(id) {
    return request({
      url: '/eduservice/course/getCourseInfo/' + id,
      method: 'get'
    })
  },
  //4.修改课程信息
  updateCourseInfo(courseInfo) {
    return request({
      url: '/eduservice/course/updateCourseInfo',
      method: 'post',
      data: courseInfo
    })
  },
  //5.课程确认信息显示
  getPublihCourseInfo(id) {
    return request({
      url: '/eduservice/course/getPublishCourseInfo/'+id,
      method: 'get'
    })
  },
  //6.课程最终发布
  publihCourse(id) {
    return request({
      url: '/eduservice/course/publishCourse/'+id,
      method: 'post'
    })
  },
  //7.课程列表
  getListCourse() {
    return request({
      url: '/eduservice/course',
      method: 'get'
    })
  }
}
