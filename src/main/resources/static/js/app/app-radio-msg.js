/**
 * Created by Span on 04.05.2017 21:34 21:34.
 */
define(['marionette'], function (Mn) {
    return Mn.Object.extend({
        channelName: 'request',

        radioRequests: {
            'get:categories:list': 'getCategories',
            'add:category': 'addNewCategory',
            'save:expense:data': 'saveExpenseData',
            'get:main:info:data': 'getMainInfoData',
            'logout': 'logout',
            'get:balance:for:year': 'getBalanceForYear',
            'get:all:data:by:categories': 'getAllExpensesOrIncomesByCategories',

        },
        getCategories: function (type) {
            var deffer = $.Deferred();
            $.ajax({
                url: '/log/getCategories',
                data: {type: type},
                dataType: "json",
                'contentType': 'application/json',
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        },
        addNewCategory: function (catName, type) {
            //console.log(catName);
            var deffer = $.Deferred();
            $.ajax({
                url: '/log/addCategory',
                type: 'post',
                data: {name: catName, type: type},
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        },
        saveExpenseData: function (model) {
            //console.log('saveExpenseData', model.toJSON());
            model.set('type', 1);
            var deffer = $.Deferred();
            $.ajax({
                url: '/log/save',
                type: 'post',
                dataType: "json",
                'contentType': 'application/json',
                data: JSON.stringify(model.toJSON()),
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        },
        getMainInfoData: function () {
            var deffer = $.Deferred();
            $.ajax({
                url: '/log/main-info',
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        },
        logout: function () {
            var deffer = $.Deferred();
            $.ajax({
                url: '/perform_logout',
                success: function (data) {
                    deffer.resolve(data);
                }
            });
            return deffer.promise();
        },
        getBalanceForYear: function (year) {
            var deffer = $.Deferred();
            $.ajax({
                url: '/log/getBalanceForYear',
                data: {year: year},
                dataType: "json",
                'contentType': 'application/json',
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        },
        getAllExpensesOrIncomesByCategories: function (type, da, db) {
            var deffer = $.Deferred();
            var postData = {
                type: type,
                da: da,
                db: db
            };
            $.ajax({
                url: '/log/getDataByCategories',
                data:  JSON.stringify(postData),
                dataType: "json",
                type:'post',
                'contentType': 'application/json',
                success: function (data) {
                    deffer.resolve(data);
                },
                error: function (data) {
                    deffer.reject(data)
                }
            });
            return deffer.promise();
        }
    });
});