/**
 * 系统用户 @author huochai @email 4141914@gmail.com @date 2018-01-18 20:43:04
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {


  'GET /api/login': function (req, res, next) {
    setTimeout(function () {
      res.json({
        success: true,
        msgMap: {
          1: '登录mock'
        }
      });
    }, 500);
  }

}
