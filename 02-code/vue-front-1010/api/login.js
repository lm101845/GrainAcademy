/**
 * @Author liming
 * @Date 2023/6/15 15:36
 **/
import request from '@/utils/request'
export default {
  //1.登录
  submitLogin(userInfo) {
    return request({
      url: `/educenter/member/login`,
      method: 'post',
      data: userInfo
    })
  },
  //2.根据token获取用户信息
   getLoginUserInfo() {
    return request({
      url: `/educenter/member/getMemberInfo`,
      method: 'get',
      // headers: {'token': cookie.get('guli_token')}
    })
    //headers: {'token': cookie.get('guli_token')}
  }
}
