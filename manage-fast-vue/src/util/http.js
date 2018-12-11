import Vue from 'vue'
import axios from 'axios'
import router from '@/router'

axios.defaults.timeout = 5000;

axios.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8';

axios.interceptors.request.use(config => {
  config.headers['token'] = Vue.cookie.get('token')
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(res => {
  //处理代码生成器返回的文件流
  if (res.headers && (res.headers['content-type'] === 'application/octet-stream;charset=UTF-8')) {
    // downloadUrl(res.request.responseURL + "&token=" + Vue.cookie.get('token'))
    downloadFile(res);
    return
  }

  if (res.data && res.data.code === 401) { // 401, token失效
    Vue.cookie.delete('token')
    router.push({path: 'login'})
  }

  return res;
}, error => {
  return Promise.reject(error);
})

// download url
const downloadUrl = url => {
  let iframe = document.createElement('iframe')
  iframe.style.display = 'none'
  iframe.src = url
  iframe.onload = function () {
    document.body.removeChild(iframe)
  }
  document.body.appendChild(iframe)
}

// download url
const downloadFile = res => {
  let blob = new Blob([res.data]);//new Blob([res])中不加data就会返回下图中[objece objece]内容（少取一层）
  let fileName = decodeURI(res.headers['content-disposition'].split('=')[1]);
  const elink = document.createElement('a');
  elink.download = fileName;
  elink.style.display = 'none';
  elink.href = URL.createObjectURL(blob);
  document.body.appendChild(elink);
  elink.click();
  URL.revokeObjectURL(elink.href);
  // 释放URL 对象
  document.body.removeChild(elink);
}

export default axios;
