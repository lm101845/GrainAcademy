/**
 * @Author liming
 * @Date 2023/6/16 18:01
 **/
import request from '@/utils/request'
const api_name = '/eduservice/teacherfront/'
export default {
  getTeacherList(page, limit) {
    return request({
      url: `${api_name}/getTeacherFrontList/${page}/${limit}`,
      method: 'post'
    })
  }
}
