/**
 * Created by Span on 25.04.2017.
 */
define(['moment', 'backbone'], function (moment) {

    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
    }

    var APP_MODELS = {};
    APP_MODELS.ExpensesModel = Backbone.Model.extend({
        defaults: {
            expPerDay: 0,
            expPerWeek: 0,
            expPerMonth: 0
        },
        initialize: function () {
            // this.set('today_exp', getRandomInt(10, 100));
            // this.set('week_exp', getRandomInt(10, 100));
            // this.set('month_exp', getRandomInt(10, 100));
        }
    });
    APP_MODELS.IncomesModel = Backbone.Model.extend({
        defaults: {
            incPerMonth: 0
        },
        initialize: function () {
            // this.set('month_exp', getRandomInt(10, 100))
        }
    });
    APP_MODELS.BalanceModel = Backbone.Model.extend({
        defaults: {
            balance: 0
        },
        initialize: function () {
            // this.set('all_time', getRandomInt(10, 100))
        }
    });
    APP_MODELS.DateTimeModel = Backbone.Model.extend({
        defaults: {
            dateAndTime: ''
        },
        initialize: function () {
            this.set('dateAndTime', moment().format('DD.MM.YYYY HH:mm'))
        }
    });
    APP_MODELS.AddExpensesWindowModel = Backbone.Model.extend({
        defaults: {
            category_id: '',
            category: '',
            dateAndTime: '',
            summary: '',
            commentary: ''
        }
    });

    return {
        expensesModel: APP_MODELS.ExpensesModel,
        incomesModel: APP_MODELS.IncomesModel,
        balanceModel: APP_MODELS.BalanceModel,
        dateTimeModel: APP_MODELS.DateTimeModel,
        addExpensesWindowModel: APP_MODELS.AddExpensesWindowModel,

    };
});