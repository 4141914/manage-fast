/**
 * 系统用户 @author huochai @email 4141914@gmail.com @date 2018-01-18 20:43:04
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/user/queryPage': function (req, res, next) {
        var data = Mock.mock({
            "content|16": [{
                id: "@integer(50,100)", //
                username: "@word(5,10)", //用户名
                password: "@word(5,10)", //密码
                email: "@word(5,10)", //邮箱
                mobile: "@word(5,10)", //手机号
                status: "@integer(50,100)", //状态  0：禁用   1：正常
                createTime: "@integer(50,100)" //创建时间
            }],
            "number": '@integer(50,100)',
            "size": 10,
            "totalElements": 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },
    'POST /api/user/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/user/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/user/queryList': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                id: "@integer(50,100)", //
                username: "@word(5,10)", //用户名
                password: "@word(5,10)", //密码
                email: "@word(5,10)", //邮箱
                mobile: "@word(5,10)", //手机号
                status: "@integer(50,100)", //状态  0：禁用   1：正常
                createTime: "@integer(50,100)" //创建时间
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'DELETE /api/user/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}