/**
 * 菜单管理 @author huochai @email 4141914@gmail.com @date 2018-01-30 21:53:15
 */
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/menu/queryPage': function (req, res, next) {
        var data = Mock.mock({
            "content|16": [{
                id: "@integer(50,100)", //
                parentId: "@integer(50,100)", //父菜单ID，一级菜单为0
                name: "@word(5,10)", //菜单名称
                url: "@word(5,10)", //菜单URL
                type: "@integer(50,100)", //类型   0：目录   1：菜单
                icon: "@word(5,10)", //菜单图标
                orderNum: "@integer(50,100)" //排序
            }],
            "number": '@integer(50,100)',
            "size": 10,
            "totalElements": 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },
    'POST /api/menu/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/menu/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/menu/queryList': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                id: "@integer(50,100)", //
                parentId: "@integer(50,100)", //父菜单ID，一级菜单为0
                name: "@word(5,10)", //菜单名称
                url: "@word(5,10)", //菜单URL
                type: "@integer(50,100)", //类型   0：目录   1：菜单
                icon: "@word(5,10)", //菜单图标
                orderNum: "@integer(50,100)" //排序
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'DELETE /api/menu/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}