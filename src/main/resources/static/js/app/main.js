require.config({
    paths: {
        'jquery': '/js/vendors/jquery',
        'underscore': '/js/vendors/underscore',
        'backbone': '/js/vendors/backbone',
        'backbone.radio': '/js/vendors/backbone.radio',
        'marionette': '/js/vendors/backbone.marionette',
        tpl: "/js/vendors/underscore-tpl",
        text: "/js/vendors/text",
        moment: '/js/vendors/moment',
        "moment-tz": '/js/vendors/moment-timezone',
        dtp: '/js/vendors/bootstrap-material-datetimepicker',//Datepicker for bootstrap-material

        'app-views': '/js/app/views',
        'app-models': '/js/app/models',
        'app-radio': '/js/app/app-radio-msg',
        'moment-ru-locale':'/js/node_modules/moment/locale/ru'
    },
    shim: {
        underscore: {
            exports: "_"
        },
        backbone: {
            deps: ["jquery", "underscore"],
            exports: "Backbone"
        },
        marionette: {
            deps: ["jquery", "backbone", 'backbone.radio'],
            exports: "Marionette"
        },
        tpl: ["text"],
        dtp: {
            deps: ["jquery", "moment"]
        }
    },
    waitSeconds: 5
});
require(['app-views']);
