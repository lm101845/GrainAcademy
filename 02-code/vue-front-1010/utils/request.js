/**
 * @Author liming
 * @Date 2023/6/13 14:40
 **/

import axios from 'axios'


// 创建axios实例
const service = axios.create({
  baseURL: "http://localhost:9001", // api 的 base_url
  timeout: 20000 // 请求超时时间
})


export default service

