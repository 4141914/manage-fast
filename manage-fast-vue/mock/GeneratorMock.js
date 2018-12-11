/**
 * 课种表 @author huochai @email 4141914@gmail.com @date 2018-01-16 21:14:23
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'GET /api/gen/code': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/gen/list': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        tableName: "@word(5,10)", //课种名称
        tableComment: "@word(5,10)", //门店名称
        engine: "InnoDB",
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  }
}
