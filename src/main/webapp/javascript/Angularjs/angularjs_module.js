var ctx = "";
var A = angular.module('Angular', []);
A.config(function ($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.defaults.transformRequest = function (data) {
        if (data === undefined) {
            return data;
        }
        return $.param(data);
    }
}).config(function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[{');
    $interpolateProvider.endSymbol('}]');
}).directive("agPaging", [function () {
    return {
        restrict: "E",
        templateUrl: ctx + "/directive/paging.html",

        scope: {
            data: "=",
            paging: "=",
            pageSize: "@"
        },
        link: function (scope, element, attrs) {
            scope.$watch("data", function (newValue) {
                if(scope.data!=undefined){
                    if (scope.data.length == 0) {
                        return;
                    }
                }else{
                    return;
                }

                scope.pages = Math.ceil(scope.data.length / scope.pageSize);//共有多少页
                scope.newPages = scope.pages > 5 ? 5 : scope.pages; //在显示页数区域，只显示五条
                scope.pageList = [];//要显示的所有页数数组
                scope.selPage = 1; //当前页数
                scope.paging = scope.data.slice(0, scope.pageSize);
                for (var i = 0; scope.newPages > i; i++) {
                    scope.pageList.push(i + 1);
                }
                scope.setData = function () {
                    scope.paging = scope.data.slice((scope.pageSize * (scope.selPage - 1)), (scope.selPage * scope.pageSize));//通过当前页数筛选出表格当前显示数据
                };
                scope.selectPage = function (page) {
                    if (1 > page || page > scope.pages) {
                        return;
                    }
                    else if (page > 2) {
                        var newpageList = [];
                        for (var i = (page - 3); ((page + 2) > scope.pages ? scope.pages : (page + 2)) > i; i++) {
                            newpageList.push(i + 1);
                        }
                        scope.pageList = newpageList;
                    } else if (page == 1 || page == 2) {
                        var newpageList = [];
                        for (var i = 0; (5 > scope.pages ? scope.pages : 5) > i; i++) {
                            newpageList.push(i + 1);
                        }
                        scope.pageList = newpageList;
                    }
                    scope.selPage = page;
                    scope.setData();
                    scope.isActivePage(page);
                };
                scope.isActivePage = function (page) {
                    return scope.selPage == page;
                };
                scope.Previous = function () {
                    scope.selectPage(scope.selPage - 1);
                };
                scope.Next = function () {
                    scope.selectPage(scope.selPage + 1);
                };
            });
        }
    }
}]).directive("moPaging", [function () {
    return {
        restrict: "E",
        templateUrl: ctx + "/directive/mopaging.html",

        scope: {
            data:"=",
            totalPages: "=",
            currPage: "=",
            pageCalback: "=",
            onSelectPage: "&"
        },
        link: function (scope, element, attrs) {
            scope.$watch("data", function (newValue) {
                if (scope.data == 0) {
                    return;
                }
                scope.pages = scope.totalPages;//共有多少页
                scope.newPages = scope.pages > 10 ? 10 : scope.pages; //在显示页数区域，只显示五条
                scope.pageList = [];//要显示的所有页数数组
                scope.selPage = scope.currPage + 1; //当前页数
                for (var i = 0; scope.newPages > i; i++) {
                    scope.pageList.push(i + 1);
                }
                scope.selectPage = function (page) {
                    if (1 > page || page > scope.pages) {
                        return;
                    }
                    else if (page > 2) {
                        var newpageList = [];
                        for (var i = (page - 3); ((page + 2) > scope.pages ? scope.pages : (page + (scope.pages - page))) > i; i++) {
                            newpageList.push(i + 1);
                        }
                        scope.pageList = newpageList;
                    } else if (page == 1 || page == 2) {
                        var newpageList = [];
                        for (var i = 0; (10 > scope.pages ? scope.pages : 10) > i; i++) {
                            newpageList.push(i + 1);
                        }
                        scope.pageList = newpageList;
                    }
                    scope.selPage = page;
                    scope.isActivePage(page);
                    scope.onSelectPage(page);
                };
                scope.isActivePage = function (page) {
                    return scope.selPage == page;
                };
                scope.onSelectPage = function (page) {
                    scope.pageCalback(page);
                }
                scope.Previous = function () {
                    scope.selectPage(scope.selPage - 1);
                };
                scope.Next = function () {
                    scope.selectPage(scope.selPage + 1);
                };
            });
        }
    }
}]).filter('dateFormat',function () {
    return function (date) {
        return date.substring(0,4)+'-'+date.substring(4,6)+'-'+date.substring(6,8);
    }
}).filter('cut', function() {
    return function(value, wordwise, max, tail) {
        if (!value)
            return '';
        max = parseInt(max, 10);
        if (!max)
            return value;
        if (max >= value.length)
            return value;
        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
                value = value.substr(0, lastspace);
            }
        }
        return value + (tail || ' …');
    };
})