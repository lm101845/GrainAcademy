/**
 * @Author liming
 * @Date 2023/5/27 15:27
 **/
import request from '@/utils/request'

export default {
  // 1.根据课程id获取到章节和小节数据列表
  getAllChapterVideo(courseId) {
    return request({
      url: '/eduservice/chapter/getChapterVideo/' + courseId,
      method: 'get'
    })
  },
}
