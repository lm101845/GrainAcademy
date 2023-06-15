/**
 * @Author liming
 * @Date 2023/6/15 14:57
 **/

import request from '@/utils/request'

export default {
  //1.根据手机号码发送短信
  sendCode(phone) {
    return request({
      url: `/edumsm/msm/send/${phone}`,
      method: 'get'
    })
  },
  //2.用户注册
  registerMember(formItem) {
    return request({
      url: `/educenter/member/register`,
      method: 'post',
      data: formItem
    })
  }
}
