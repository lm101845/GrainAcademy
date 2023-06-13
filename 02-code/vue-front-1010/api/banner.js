/**
 * @Author liming
 * @Date 2023/6/13 14:53
 **/
import request from '@/utils/request'
export default {
  //查询前2条banner数据
  getListBanner() {
    return request({
      url: `/educms/bannerfront/getAllBanner`,
      method: 'get'
    })
  }
}
