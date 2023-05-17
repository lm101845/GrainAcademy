/**
 * @Author liming
 * @Date 2023/5/17 15:18
 **/

import request from '@/utils/request'

export default {
  // 1.课程分类列表
  getSubjectList() {
    return request({
      url: '/eduservice/subject/getAllSubject',
      method: 'get'
    })
  },

}
