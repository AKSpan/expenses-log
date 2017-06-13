define(['marionette',
    'backbone.radio',
    'app-radio',
    'app-models',
    'moment-ru-locale',
    "moment",
    "moment-tz",
    "tpl!/templates/expenses-block-tpl.tpl",
    "tpl!/templates/income-block-tpl.tpl",
    "tpl!/templates/balance-block-tpl.tpl",
    "tpl!/templates/full-info-tpl.tpl",
    "tpl!/templates/expenses-window-tpl.tpl",
    "tpl!/templates/date-time-expenses-block-tpl.tpl",
    "tpl!/templates/category-expenses-block-tpl.tpl",
    "tpl!/templates/summary-expenses-block-tpl.tpl",
    "tpl!/templates/comment-expenses-block-tpl.tpl",
    "tpl!/templates/category-window-tpl.tpl",
    "tpl!/templates/left-menu-tpl.tpl",
    "tpl!/templates/calculator-window-tpl.tpl",
    "tpl!/templates/balance-window-tpl.tpl",
    "tpl!/templates/balance-cost-by-month-tpl.tpl",
    "tpl!/templates/full-expenses-incomes-window-tpl.tpl",
    "tpl!/templates/all-exp-inc-by-categories-tpl.tpl",
    "dtp"

], function (Mn, BbRadio, appRadioHandler, appModels, momentRuLocale, moment, momentTz, expBlockTpl, incBlockTpl, balanceBlockTpl, fullInfoTpl, expensesWindow,
             dateTimeExpensesBlockTpl, categoryExpensesBlockTpl,
             summaryExpensesBlockTpl,
             commentExpensesBlockTpl, categoryWindowTpl, leftMenuTpl, calcWindowTpl,
             balanceWindowTpl, balanceByMonthTpl,
             fullExpIncWindowTpl, allExpIncByCatWindowTpl) {
    var APP_VIEW = {};
    var APPLICATION = {};
    var requestChannel = new BbRadio.channel('request');
    new appRadioHandler();
    var userExpenseGlobalModel;


    APP_VIEW.ExpensesView = Mn.View.extend({
        model: new appModels.expensesModel(),
        tagName: 'fieldset',
        template: expBlockTpl,
        ui: {
            'addExpensesIcon': '.info-row__add-expenses'
        },
        events: {
            "click @ui.addExpensesIcon": "addExpenses"
        },
        initialize: function () {
            userExpenseGlobalModel = new appModels.addExpensesWindowModel();
        },
        addExpenses: function () {
            userExpenseGlobalModel.set('type', 0);
            APPLICATION.getRegion('body').show( new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    APP_VIEW.IncomesView = Mn.View.extend({
        model: new appModels.incomesModel(),
        tagName: 'fieldset',
        template: incBlockTpl,
        ui: {
            'addIncomesIcon': '.info-row__add-incomes'
        },
        events: {
            "click @ui.addIncomesIcon": "addIncomes"
        },
        addIncomes: function () {
            userExpenseGlobalModel.set('type', 1);
            APPLICATION.getRegion('body').show( new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    APP_VIEW.BalanceWindowView = Mn.View.extend({
        template: balanceWindowTpl,
        className: 'center-block',
        regions: {
            monthsRegion: '.center-block__balance-cost-by-month-wrapper'
        },
        ui: {
            'backButton': 'div.balance-window__back-wrapper input.info-row__action-button',
            'nextYear': '.choose-balance-year__next-year-wrapper input',
            'prevYear': '.choose-balance-year__prev-year-wrapper input'
        },
        events: {
            'click @ui.backButton': 'backToMainMenu',
            'click @ui.nextYear': 'chooseNextYear',
            'click @ui.prevYear': 'choosePrevYear'
        },
        modelEvents: {
            'change:currentYear': 'getBalanceForYear',
            'change:totalCost': 'render'
        },
        onRender: function () {
            this.collection = new Backbone.Collection(this.model.get('balancePerMonths'));
            this.showChildView('monthsRegion', new APP_VIEW.BalanceAllMonthView({
                collection: this.collection
            }));
        },
        backToMainMenu: function () {
            console.log('backToMainMenu')
            APPLICATION.getRegion('body').show( new APP_VIEW.FullInfoView());

            // $(document).find('body').html(new APP_VIEW.FullInfoView().render().$el);
        },
        chooseNextYear: function () {
            this.model.set('currentYear', parseInt(this.model.get('currentYear')) + 1);
        },
        choosePrevYear: function () {
            this.model.set('currentYear', parseInt(this.model.get('currentYear')) - 1);
        },
        getBalanceForYear: function () {
            var self = this, year = this.model.get('currentYear');
            $.when(requestChannel.request("get:balance:for:year", year)).done(function (data) {
                self.model.set('balancePerMonths', data['balancePerMonths']);
                self.collection.reset(self.model.get('balancePerMonths'));
                self.model.unset('totalCost', {silent: true});
                self.model.set('totalCost', data['totalCost']);
            });
        }
    });
    APP_VIEW.BalanceView = Mn.View.extend({
        model: new appModels.balanceModel(),
        tagName: 'fieldset',
        template: balanceBlockTpl,
        ui: {
            'showBalanceIcon': '.info-row__show-balance'
        },
        events: {
            "click @ui.showBalanceIcon": "showBalance"
        },
        showBalance: function () {
            $.when(requestChannel.request("get:balance:for:year", moment().format('YYYY'))).done(function (data) {
                data = new Backbone.Model(data);
                data.set('currentYear', moment().format('YYYY'));
                APPLICATION.getRegion('body').show( new APP_VIEW.BalanceWindowView({model: data}));

                // $(document).find('body').html(new APP_VIEW.BalanceWindowView({model: data}).render().$el)
            });
        }
    });
    //TODO: BalanceView,IncomesView,ExpensesView - одинаковые Вью с одинаковыми events но разными шаблонами. подумать как объединить в 1 вью
    APP_VIEW.DateTimeExpensesBlock = Mn.View.extend({
        tagName: 'fieldset',
        template: dateTimeExpensesBlockTpl,
        initialize: function () {
            if (userExpenseGlobalModel.get('dateAndTime').length === 0)
                userExpenseGlobalModel.set('dateAndTime', moment().format('DD.MM.YYYY HH:mm'));
            this.model = userExpenseGlobalModel;
        },
        events: {
            'click .info-row__date-time-row': 'showCalendar'
        },
        showCalendar: function () {
            console.log('showCalendar')

            this.$el.find('.info-row__date-time-row').bootstrapMaterialDatePicker({
                weekStart: 1,
                format: 'DD.MM.YYYY HH:mm',
                lang: 'ru'
            });
        }
    });
    APP_VIEW.SummaryExpensesBlock = Mn.View.extend({
        tagName: 'fieldset',
        template: summaryExpensesBlockTpl,
        ui: {
            'calcBtn': '.info-row__calc-img',
            'summaryRow': '.info-row__summary-cost-row'
        },
        events: {
            'click @ui.calcBtn': 'showCalcWindow',
            'keyup @ui.summaryRow': 'changeSummaryCost'
        },
        changeSummaryCost: function () {
            userExpenseGlobalModel.set('summary', this.ui.summaryRow.val());
        },
        showCalcWindow: function () {
            APPLICATION.getRegion('body').show( new APP_VIEW.CalcWindowView({model: userExpenseGlobalModel}));

            // APPLICATION.showView(new APP_VIEW.CalcWindowView({model: userExpenseGlobalModel}))
            // $(document).find('body').html(new APP_VIEW.CalcWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    APP_VIEW.CommentExpensesBlock = Mn.View.extend({
        tagName: 'fieldset',
        template: commentExpensesBlockTpl,
        ui: {
            "commentRow": ".info-row__comment-row"
        },
        events: {
            "keyup @ui.commentRow": "changeComment"
        },
        changeComment: function () {
            userExpenseGlobalModel.set('commentary', this.ui.commentRow.val())
        }
    });
    APP_VIEW.LeftMenuView = Mn.View.extend({
        template: leftMenuTpl,
        className: 'center-block left-menu-block',
        attributes: {
            "style": "display:none"
        },
        events: {
            "click": "hideMenu",
            "click p": "clickLeftMenuItem"
        },
        hideMenu: function () {
            this.$el.slideToggle('slide')
        },
        clickLeftMenuItem: function (event) {
            event.stopPropagation();
            var action = $(event.target).attr('action')
            this[action]();
        },
        createBackup: function () {
            //console.log('createBackup')
        },
        getInfo: function () {
            //console.log('getInfo')
        },
        logout: function () {
            //console.log('logout')
            $.when(requestChannel.request('logout')).done(function () {
                //$(document).html(data)
                window.location = "login.html"
            });
        },
    });
    APP_VIEW.AllExpIncRowItemView = Mn.View.extend({
        className: 'center-block__info-block',
        template: allExpIncByCatWindowTpl
    });
    APP_VIEW.AllExpIncCollView = Mn.CollectionView.extend({
        className: 'center-block__info-block',
        childView: APP_VIEW.AllExpIncRowItemView
    });
    APP_VIEW.FullExpIncWindow = Mn.View.extend({
        template: fullExpIncWindowTpl,
        className: 'center-block',
        ui: {
            'backButton': 'div.header-window-block__back-wrapper > input',
            'statsButton': 'div.header-window-block__info-graphic-wrapper > input',
            'prevValueButton': 'div.choosing-period__previous-value-wrapper > input',
            'nextValueButton': 'div.choosing-period__next-value-wrapper > input',
            'periodSelect': 'select.header-window-block__choose-period',
            'firstPeriodValue': 'p.choose-balance-year__current-value-first-wrapper',
            'secondPeriodValue': 'p.choose-balance-year__current-value-second-wrapper'
        },
        events: {
            'click @ui.backButton': 'backToMainMenu',
            'click @ui.statsButton': 'showStatsWindow',
            'click @ui.prevValueButton': 'setPrevValue',
            'click @ui.nextValueButton': 'setNextValue',
            'change @ui.periodSelect': 'changePeriodType'
        },
        regions: {
            categoriesAndCostsRegion: '.center-block__balance-cost-per-category-wrapper'
        },
        initialize: function () {
            console.log(' APP_VIEW.FullExpIncWindow#init');
            this.listenTo(this.model, 'change:periodType', function () {
                //this.setType();
                this.__showAndHideElement();
                this.getData();
            });
            this.listenTo(this.model, 'change:firstPeriodValue', function () {
                this.getData();
            });
            this.listenTo(this.model, 'change:totalCost', function () {
                this.render();
            });
        },
        onRender: function () {
            console.log('FullExpIncWindow#render', this.model.get('categories'), this.model.toJSON())
            this.collection = this.model.get('categories')

            this.showChildView('categoriesAndCostsRegion', new APP_VIEW.AllExpIncCollView({
                collection: this.collection
            }));
            this.ui.firstPeriodValue.text(this.model.get('firstPeriodValue'));

        },
        backToMainMenu: function () {
            APPLICATION.getRegion('body').show( new APP_VIEW.FullInfoView());

            // APPLICATION.showView(new APP_VIEW.FullInfoView());
            // $(document).find('body').html(new APP_VIEW.FullInfoView().render().$el);
        },
        showStatsWindow: function () {
            console.log('showStatsWindow')
        },
        setPrevValue: function () {
            this.ui.firstPeriodValue.text(moment(this.ui.firstPeriodValue.text(), this.__getDateFormat()).subtract(1, this.__getDateKey()).format(this.__getDateFormat()));
            this.__setSecondPeriodValue();
            this.model.set('firstPeriodValue', this.ui.firstPeriodValue.text());
        },
        setNextValue: function () {
            this.ui.firstPeriodValue.text(moment(this.ui.firstPeriodValue.text(), this.__getDateFormat()).add(1, this.__getDateKey()).format(this.__getDateFormat()));
            this.__setSecondPeriodValue();
            this.model.set('firstPeriodValue', this.ui.firstPeriodValue.text());
        },
        changePeriodType: function () {
            this.model.set('periodType', this.ui.periodSelect.val());

            if (this.model.get('periodType') === 'WEEK') {
                this.ui.firstPeriodValue.text(momentTz().tz('Europe/Moscow').startOf('week').format(this.__getDateFormat()));
                this.__setSecondPeriodValue();
            }
            else
                this.ui.firstPeriodValue.text(momentTz().format(this.__getDateFormat()));
        },
        getData: function () {
            console.log('getData', this.model.toJSON());
            console.log('getData', this.ui.firstPeriodValue.text());
            console.log('getData', this.ui.secondPeriodValue.text());
            var self = this;
            $.when(requestChannel.request('get:all:data:by:categories', this.model.get('type'), this.model.get('firstPeriodValue'), this.ui.secondPeriodValue.text())).done(function (data) {
                self.collection.reset(data)
                var newTotalCost = eval(self.collection.pluck('sum').join('+'));
                self.model.set('totalCost', self.collection.length > 0 ? newTotalCost : 0)
            });
        },
        __setSecondPeriodValue: function () {
            this.ui.secondPeriodValue.text(momentTz(this.ui.firstPeriodValue.text(), this.__getDateFormat()).tz('Europe/Moscow').add(1, this.__getDateKey()).subtract(1, 'seconds').format(this.__getDateFormat()))
        },
        __getDateFormat: function () {
            switch (this.model.get('periodType')) {
                case "YEAR":
                    return "YYYY";
                default:
                    return "DD.MM.YYYY";
            }
        },
        __getDateKey: function () {
            switch (this.model.get('periodType')) {
                case "YEAR":
                    return "y";
                case "MONTH":
                    return "M";
                case "WEEK":
                    return "w";
                case "DAY":
                default:
                    return "d";
            }
        },
        __showAndHideElement: function () {
            switch (this.model.get('periodType')) {
                case "WEEK":
                    this.ui.secondPeriodValue.removeAttr("hidden");
                    this.ui.firstPeriodValue.addClass('as-period-value');
                    this.ui.secondPeriodValue.addClass('as-period-value');
                    this.__setSecondPeriodValue();
                    break;
                case "PERIOD":
                    this.ui.prevValueButton.attr("hidden", "");
                    this.ui.nextValueButton.attr("hidden", "");
                    this.ui.secondPeriodValue.removeAttr("hidden");
                    this.ui.firstPeriodValue.addClass('as-period-value');
                    this.ui.secondPeriodValue.addClass('as-period-value');
                    this.__setSecondPeriodValue();
                    break;
                case "DAY":
                case "MONTH":
                case "YEAR":
                    this.ui.prevValueButton.removeAttr("hidden");
                    this.ui.nextValueButton.removeAttr("hidden");
                    this.ui.secondPeriodValue.attr("hidden", "");
                    this.ui.firstPeriodValue.removeClass('as-period-value');
                    this.ui.secondPeriodValue.removeClass('as-period-value');
                    break;

            }
        }
    });
    APP_VIEW.FullInfoView = Mn.View.extend({
        template: fullInfoTpl,
        className: 'center-block',
        regions: {
            expensesBlockRegion: '.center-block__expenses-block',
            incomesBlockRegion: '.center-block__incoming-block',
            balanceBlockRegion: '.center-block__balance-block'
        },
        ui: {
            expensesIcon: 'img#js-expenses',
            incomesIcon: 'img#js-incomes',
            leftMenuIcon: '.logout-form__menu-wrapper input'
        },
        events: {
            'click @ui.expensesIcon': 'showInfoAboutExpenses',
            'click @ui.incomesIcon': 'showInfoAboutIncomes',
            'click @ui.leftMenuIcon': 'showLeftMenu'
        },
        onRender: function () {
            var self = this;
            $.when(requestChannel.request('get:main:info:data', userExpenseGlobalModel)).done(function (data) {
                //TODO: попробовать подгружать шаблоны сразу перед их использованием
                self.getRegion('expensesBlockRegion').show(new APP_VIEW.ExpensesView({model: new appModels.expensesModel(data)}));
                self.getRegion('incomesBlockRegion').show(new APP_VIEW.IncomesView({model: new appModels.incomesModel(data)}));
                self.getRegion('balanceBlockRegion').show(new APP_VIEW.BalanceView({model: new appModels.balanceModel(data)}));
                APPLICATION.getRegion('body').$el.append( new APP_VIEW.LeftMenuView().render().$el);

                // $(document).find('body').append(new APP_VIEW.LeftMenuView().render().$el);
            });


        },
        showInfoAboutExpenses: function () {
            this.__getAndDrawInfoAboutExpOrInc(0);
        },
        showInfoAboutIncomes: function () {
            console.log('showInfoAboutIncomes')
            this.__getAndDrawInfoAboutExpOrInc(1);
        },
        showLeftMenu: function () {
            //console.log('showLeftMenu')
            $(document).find('.left-menu-block').slideToggle('slide');
        },
        __getAndDrawInfoAboutExpOrInc: function (type) {
            $.when(requestChannel.request("get:all:data:by:categories", type, moment().format('DD.MM.YYYY'), moment().format('DD.MM.YYYY'))).done(function (data) {
                var collection = new Backbone.Collection();
                var sum = 0;
                _.each(data, function (item) {
                    sum += item.sum;
                    collection.add(new Backbone.Model(item));
                });
                data = new Backbone.Model();
                data.set('firstPeriodValue', moment().format('DD.MM.YYYY'));
                data.set('periodType', 'DAY');
                data.set('totalCost', sum);
                data.set('categories', collection);
                data.set('type', type);

                APPLICATION.getRegion('body').show(new APP_VIEW.FullExpIncWindow({model: data}));
                // $(document).find('body').html(new APP_VIEW.FullExpIncWindow({model: data}).render().$el)
            });
        }
    });
    APP_VIEW.BalanceCostByMonthView = Mn.View.extend({
        template: balanceByMonthTpl,
        className: 'center-block__info-block'
    });
    APP_VIEW.BalanceAllMonthView = Mn.CollectionView.extend({
        className: 'center-block__info-block',
        childView: APP_VIEW.BalanceCostByMonthView,
        initialize: function () {
            console.log('APP_VIEW.BalanceAllMonthView#init')
        },
        onRender: function () {
            console.log('APP_VIEW.BalanceAllMonthView#render')
        }
    });
    APP_VIEW.ExpensesWindowView = Mn.View.extend({
        template: expensesWindow,
        className: 'center-block',
        regions: {
            dateTimeBlockRegion: '.center-block__date-time-block',
            categoryBlockRegion: '.center-block__category-block',
            summaryBlockRegion: '.center-block__summary-block',
            commentBlockRegion: '.center-block__commentary-block'
        },
        initialize: function () {
            //console.log('ExpensesWindowView#init', userExpenseGlobalModel);
        },
        events: {
            'click div.logout-form__back-wrapper > input': 'backToMainForm',
            'click div.logout-form__success-wrapper > input': 'saveExpenseData'
        },
        onRender: function () {
            this.getRegion('dateTimeBlockRegion').show(new APP_VIEW.DateTimeExpensesBlock({model: userExpenseGlobalModel}));
            this.getRegion('categoryBlockRegion').show(new APP_VIEW.CategoryExpensesBlock({model: userExpenseGlobalModel}));
            this.getRegion('summaryBlockRegion').show(new APP_VIEW.SummaryExpensesBlock({model: userExpenseGlobalModel}));
            this.getRegion('commentBlockRegion').show(new APP_VIEW.CommentExpensesBlock({model: userExpenseGlobalModel}));
        },
        backToMainForm: function () {
           // $(document).find('body').empty();
            //todo: probably clear userExpenseGlobalModel?!
            APPLICATION.getRegion('body').show( new APP_VIEW.FullInfoView());

            // $(document).find('body').append(new APP_VIEW.FullInfoView().render().$el);
        },
        saveExpenseData: function () {
            var self = this;
            if (userExpenseGlobalModel.get("summary") !== "") {
                userExpenseGlobalModel.set('dateAndTime', this.$el.find('input.info-row__date-time-row').val());
                $.when(requestChannel.request('save:expense:data', userExpenseGlobalModel)).done(function () {
                    self.backToMainForm();
                });
            }
        }
    });
    var CategoryItemView = Mn.View.extend({
        className: 'categories-block__category-item',
        template: _.template('<p class="category-item__category-value"><%= category %></p><img src="/img/vertical-dots.png" alt="edit" class="category-item__edit-item">'),
        attributes: function () {
            return {
                'category-id': this.model.get('id')
            }
        },
        ui: {
            'editCategoryBtn': 'img.category-item__edit-item',
            'rowValue': 'p.category-item__category-value'
        },
        events: {
            'click @ui.editCategoryBtn': 'editCategory',
            'click @ui.rowValue': 'chooseCategory'
        },
        editCategory: function () {
            //console.log('editCategory', this.model.toJSON())
        },
        chooseCategory: function () {
            userExpenseGlobalModel.set('category', this.model.get('category'));
            userExpenseGlobalModel.set('category_id', this.model.get('id'));
            APPLICATION.getRegion('body').show( new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    var CategoriesView = Mn.CollectionView.extend({
        template: categoryWindowTpl,
        className: 'category_wrapper__categories-block',
        childView: CategoryItemView,
        fullCollection: undefined,
        initialize: function (options) {
            this.fullCollection = new Backbone.Collection(options.collection.toJSON());
            this.listenTo(this.fullCollection, "add", this.updFullCol);
        },
        updFullCol: function (model) {
            //console.log(model.toJSON())
            this.fullCollection.comparator = function (model) {
                return model.get('category');
            };
            this.fullCollection.sort();
            //console.log('updFullCol', this.fullCollection.toJSON())
            this.triggerMethod("add:collection", model.get("id"), model.get("category"))
        }
    });
    APP_VIEW.CategoryWindowView = Mn.View.extend({
        template: categoryWindowTpl,
        className: 'center-block',
        regions: {
            categoryWrapper: '.center-block__category_wrapper'
        },
        ui: {
            'textField': '.find-or-add-new-category__text-field',
            'addCategoryBtn': '.info-row__choose-category-btn',
            backButton: '.category-window__back-wrapper > input'

        },
        events: {
            'click @ui.addCategoryBtn': 'addNewCategory',
            'keyup @ui.textField': 'findCategoryByText',
            'click @ui.backButton': 'backToExpensesMainView'
        },
        addNewCategory: function () {
            var self = this;
            var val = this.ui.textField.val();
            var categoryWrapperColl = this.getRegion('categoryWrapper').currentView.fullCollection;
            if (val.length > 0 && categoryWrapperColl.where({category: val}).length === 0) {
                $.when(requestChannel.request('add:category', val, userExpenseGlobalModel.get('type'))).done(function (data) {
                    if (data !== null) {
                        self.getRegion('categoryWrapper').currentView.fullCollection.add({id: data, category: val});
                    }
                });
            }

        },
        findCategoryByText: function () {
            this.getRegion('categoryWrapper').currentView.collection.reset(this.getRegion('categoryWrapper').currentView.fullCollection.toJSON());
            var val = this.ui.textField.val().toLowerCase();
            var categoryWrapperColl = this.getRegion('categoryWrapper').currentView.collection;
            var finded = categoryWrapperColl.where(function (num) {
                var category = num.get('category').toLowerCase();
                return category.indexOf(val) !== -1 ? num : null;
            });
            categoryWrapperColl.reset(finded);
        },
        backToExpensesMainView: function () {
            APPLICATION.getRegion('body').show( new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        },
        onRender: function () {
            var self = this;
            $.when(requestChannel.request('get:categories:list', userExpenseGlobalModel.get('type'))).done(function (data) {
                var catItemView = new CategoriesView({collection: new Backbone.Collection(data)});
                self.getRegion('categoryWrapper').show(catItemView);
            });
        },
        onChildviewAddCollection: function (id, cat) {
            userExpenseGlobalModel.set('category_id', id);
            userExpenseGlobalModel.set('category', cat);
            this.backToExpensesMainView();
        }
    });
    APP_VIEW.CategoryExpensesBlock = Mn.View.extend({
        tagName: 'fieldset',
        template: categoryExpensesBlockTpl,
        ui: {
            categoryValue: '.info-row__category-row',
            chooseCategory: '.info-row__choose-category-btn'
        },
        events: {
            "click @ui.chooseCategory": "showChooseCategoryBlock"
        },
        modelEvents: {
            'change:category': 'changeCategoryValue',
            'change:category_id': 'changeCategoryID'
        },

        onRender: function () {
            //console.log('CategoryExpensesBlock#onRender1', this.model.toJSON());
            var self = this;
            if (this.model.get('category').length === 0)
                $.when(requestChannel.request('get:categories:list', this.model.get('type'))).done(function (data) {
                    self.model.set('category', data[0]['category']);
                    self.model.set('category_id', data[0]['id']);
                    //console.log('CategoryExpensesBlock#onRender2', self.model.toJSON())
                });
            else {
                //console.log('else model', this.model.toJSON());
                var c = self.model.get('category');
                var id = self.model.get('id');
                this.model = new Backbone.Model();
                this.model.set('category', c);
                this.model.set('category_id', id);
                //console.log('CategoryExpensesBlock#onRender3', this.model.toJSON())
            }
        },
        changeCategoryValue: function (model, value) {
            //console.log('change',value)
            userExpenseGlobalModel.set('category', value);
            this.ui.categoryValue.val(value);
        },
        changeCategoryID: function (model, value) {
            //console.log('change',value)
            userExpenseGlobalModel.set('category_id', value);
            this.ui.categoryValue.attr('category_id', value);
        },
        showChooseCategoryBlock: function () {
            APPLICATION.getRegion('body').show( new APP_VIEW.CategoryWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.CategoryWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    APP_VIEW.CalcWindowView = Mn.View.extend({
        template: calcWindowTpl,
        className: 'center-block',
        ui: {
            "symbolBtns": "input[symbol]",
            "actionBtns": "input[action]",
            "resultRow": ".calc-result",
            "backBtn": ".calculator-window__back-wrapper > input",
            "doneBtn": ".calculator-window__done-calc-wrapper > input"
        },
        events: {
            "click @ui.symbolBtns": "clickSymbolBnts",
            "click @ui.actionBtns": "clickActionBnts",
            "click @ui.backBtn": "clickBackBnts",
            "click @ui.doneBtn": "clickDoneBnts"
        },
        clickSymbolBnts: function (e) {
            this.ui.resultRow.val(this.ui.resultRow.val() + $(e.target).val());
        },
        clickActionBnts: function (e) {
            var btnID = $(e.target).attr('id');
            switch (btnID) {
                case "calc-equal":
                    this.ui.resultRow.val(eval(this.ui.resultRow.val()).toFixed(2));
                    userExpenseGlobalModel.set('summary', this.ui.resultRow.val())
                    break;
                case "calc-clear":
                    this.ui.resultRow.val('');
                    break;
            }
        },
        clickBackBnts: function () {
            APPLICATION.getRegion('body').show( new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));
            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        },
        clickDoneBnts: function () {
            this.ui.resultRow.val().length > 0 ? userExpenseGlobalModel.set('summary', this.ui.resultRow.val()) : '';
            APPLICATION.getRegion('body').show(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}));

            // $(document).find('body').html(new APP_VIEW.ExpensesWindowView({model: userExpenseGlobalModel}).render().$el)
        }
    });
    /**************************/
    APPLICATION = new Mn.Application({
        region: 'body'
    });
    APPLICATION.on('start', function () {
        console.log('START')
        Backbone.history.start();
        this.showView(new APP_VIEW.FullInfoView())
    });
    APPLICATION.start();
    /**************************/


    return {
        //TODO: Удалить лишнее
        expensesView: APP_VIEW.ExpensesView,
        incomesView: APP_VIEW.IncomesView,
        balanceView: APP_VIEW.BalanceView,
        fullInfoView: APP_VIEW.FullInfoView,
        expensesWindowView: APP_VIEW.ExpensesWindowView,
        dateTimeExpensesBlock: APP_VIEW.DateTimeExpensesBlock,
        categoryExpensesBlock: APP_VIEW.CategoryExpensesBlock,
        summaryExpensesBlock: APP_VIEW.SummaryExpensesBlock,
        categoryWindowView: APP_VIEW.CategoryWindowView,
        calcWindowView: APP_VIEW.CalcWindowView
    };
});
