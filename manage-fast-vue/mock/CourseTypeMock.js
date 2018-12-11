/**
 * 课种表 @author huochai @email 4141914@gmail.com @date 2018-01-16 21:14:23
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/courseType/queryPage': function (req, res, next) {
        var data = Mock.mock({
            "content|16": [{
                id: "@integer(50,100)", //ID
                name: "@word(5,10)", //课种名称
                branchName: "@word(5,10)" //门店名称
            }],
            "number": '@integer(50,100)',
            "size": 10,
            "totalElements": 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },
    'POST /api/courseType/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/courseType/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/courseType/queryList': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                id: "@integer(50,100)", //ID
                name: "@word(5,10)", //课种名称
                branchName: "@word(5,10)" //门店名称
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'DELETE /api/courseType/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}