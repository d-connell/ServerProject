"use strict";

const app = angular.module('webApp', ['ngRoute']);

app.config(['$routeProvider',
    function config($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: '/templates/home.html',
                controller: 'HomeController'
            })
            .when('/category/:category', {
                templateUrl: '/templates/category.html',
                controller: 'CategoryController'
            })
            .when('/category/:category/type/:type/create', {
                templateUrl: '/templates/create.html',
                controller: 'CreateController'
            })
            .when('/category/:category/type/:type/update/:id', {
                templateUrl: '/templates/update.html',
                controller: 'UpdateController'
            })
            .when('/category/:category/type/:type/remove', {
                templateUrl: '/templates/remove.html',
                controller: 'SoftDeleteController'
            })
            .when('/category/:category/type/:type/:id/:text', {
                templateUrl: '/templates/detail.html',
                controller: 'DetailController'
            })
            .when('/category/:category/type/:type', {
                templateUrl: '/templates/type.html',
                controller: 'TypeController'
            })
            .when('/admin/', {
                templateUrl: '/templates/admin.html',
                controller: 'AdminController'
            })
            .when('/admin/deletions', {
                templateUrl: '/templates/delete.html',
                controller: 'HardDeleteController'
            })
            .when('/error/:text', {
                templateUrl: '/templates/error.html',
                controller: 'ErrorController'
            })
            .otherwise('/home');
    }
]);