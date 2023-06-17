/**
 * @Author liming
 * @Date 2023/6/16 18:01
 **/
import request from '@/utils/request'
const api_name = '/eduservice/teacherfront/'
export default {
  //1.分页讲师查询的方法
  getTeacherList(page, limit) {
    return request({
      url: `${api_name}/getTeacherFrontList/${page}/${limit}`,
      method: 'post'
    })
  },

  //2.讲师详情的方法
  getTeacherInfo(id) {
    return request({
      url: `/eduservice/teacherfront/getTeacherFrontInfo/${id}`,
      method: 'get'
    })
  }
}
