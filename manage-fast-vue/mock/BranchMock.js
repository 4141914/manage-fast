/**
 * 单位 @author lijianan @email 4141914@gmail.com @date 2018-04-15 17:07:55
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/branch/queryPage': function (req, res, next) {
        var data = Mock.mock({
            "content|16": [{
                id: "@integer(50,100)", //id
                name: "@word(5,10)", //单位名称
                parentId: "@integer(50,100)", //所属单位id
                address: "@word(5,10)", //地址
                description: "@word(5,10)" //描述
            }],
            "number": '@integer(50,100)',
            "size": 10,
            "totalElements": 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },
    'POST /api/branch/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/branch/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/branch/queryList': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                id: "@integer(50,100)", //id
                name: "@word(5,10)", //单位名称
                parentId: "@integer(50,100)", //所属单位id
                address: "@word(5,10)", //地址
                description: "@word(5,10)" //描述
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'DELETE /api/branch/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}