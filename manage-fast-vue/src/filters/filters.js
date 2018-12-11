import moment from 'moment';

function date_format(time) {
  if (time != null)
    return new moment(time).format("YYYY-MM-DD")
}

function date_time_format(time) {
  if (time != null)
    return new moment(time).format("YYYY-MM-DD HH:mm:ss")
}

function user_status_format(val) {
  if (val == 0) {
    return "禁用";
  }
  if (val == 1) {
    return "启用";
  }
  return "";
}

export default function (Vue) {
  Vue.filter('date_format', date_format);
  Vue.filter('date_time_format', date_time_format);
  Vue.filter('user_status_format', user_status_format);
}
