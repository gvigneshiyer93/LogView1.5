/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var LogViewerApp = angular.module("LogViewerApp", ['ngRoute', 'ngMessages']);

LogViewerApp.value("conf", {
    method: 'POST',
    url: 'http://localhost:8084/LogView1.5/logviewservlet',
    headers: {'Content-Type': 'application/json'}

});


LogViewerApp.config(function ($routeProvider) {
    $routeProvider.
            when('/', {
                templateUrl: "home.html",
                controller: "LogViewerCtrl"
            }).
            when('/login', {
                templateUrl: "login.html",
                controller: "LoginLogCtrl"

            }).
            when('/loginlistView', {
                templateUrl: "loginListView.html",
                controller: "LoginLogListViewCtrl as ctrl"
            }).
            when('/auditlog', {
                templateUrl: "auditlog.html",
                controller: "AuditLogCtrl"
            }).
            when('/exceptionlog', {
                templateUrl: "exceptionlog.html",
                controller: "ExceptionLogCtrl"

            }).
            when('/tasks', {
                templateUrl: "tasks.html",
                controller: "TasksCtrl"
            }).
            when('/changelog', {
                templateUrl: "changelog.html",
                controller: "ChangeLogCtrl"

            }).
            when('/maillog', {
                templateUrl: "maillog.html",
                controller: "MailLogCtrl"
            }).
            when('/docusign', {
                templateUrl: "docusign.html",
                controller: "DocusignCtrl"

            }).
            when('/activitylog', {
                templateUrl: "activity.html",
                controller: "ActivityCtrl"

            }).
            otherwise({
                redirectTo: '/'
            });
});


LogViewerApp.controller("LogViewerCtrl", function ($scope, $http, conf) {
    //$scope.testfn=function(){
    //alert("in logview");
    // alert($scope.conf);
    conf.params = {uri: '/'};
    //alert("uri:'/'");
    $http(conf).then(function success(response) {
        $scope.status = response.data;
        //      alert($scope.status);
        $scope.allkeys = Object.keys($scope.status);
        $scope.allValues = Object.values($scope.status);
        //alert($scope.allkeys);
    });
    //};
});


LogViewerApp.controller("LoginLogCtrl", function ($scope, $http, conf) {

     alert("in loginlog");    

    // alert($scope.conf);
    conf.params = {uri: "login"};
    
    $scope.reset= function(){
        alert("reset fn");
       $scope.userId="";
       $scope.fromDate="";
       $scope.toDate="";
       $scope.uidcheckbox=document.getElementById("useridactivator");
       alert($scope.uidcheckbox.value);
       $scope.uidcheckbox.value="off";
//        alert($scope.useridactivator.value);
//        $scope.useridactivator.value=false;
    };

    $scope.filters = function () {
        $scope.object = {};
        $scope.fd = document.getElementById("fromDate").value;
        $scope.td = document.getElementById("toDate").value;
        //alert($scope.frsDate);
        //alert($scope.fd);
        //alert($scope.td);
        if ($scope.useridactivator) {

            if ($scope.userId === undefined) {
                //alert("No userId");
                $scope.uidmsg = " Enter User id";
            }


            $scope.object.UserId = $scope.userId;
            // $scope.UserIdCheckBox = document.getElementById('UserIdCheckBox').value;    
        }
        // alert("49");
        if ($scope.dateactivator) {   //document.getElementById('DateCheckBox').value;
            // alert("dateactivator");
            if (($scope.fd === "") || ($scope.td === "")) {
                //       alert("55");
                $scope.udatemsg = "Values Missing";
            }

            $scope.object.AttemptedLoginTime = $scope.fd + "," + $scope.td;
            // $scope.object.toDate = $scope.toDate;

        }

        //alert("line 62");
        $scope.json = angular.toJson($scope.object);
        // alert($scope.json);




        //if($scope.userCheckBox.checked){
//         this.userid=$scope.userId;
//alert($scope.conf);
//alert(conf.data);
        conf.data = $scope.json;
        $http(conf).then(function success(response) {
            //          alert("TABLE RECEIVED ");
            $scope.logtablejson = response.data;
            //        alert("before table");
            //      alert(logtablejson);
            //    alert($scope.conf);
            //  alert(conf.data);
            //alert("lastline");
        }, function error(response) {
            // alert("response error");
            $scope.stat = response.status;

        });



    };
});




LogViewerApp.controller("AuditLogCtrl", function ($scope, $http, conf) {
});


LogViewerApp.controller("ExceptionLogCtrl", function ($scope, $http, conf) {
});


LogViewerApp.controller("TasksCtrl", function ($scope, $http, conf) {
});

LogViewerApp.controller("ChangeLogCtrl", function ($scope, $http, conf) {
});


LogViewerApp.controller("MailLogCtrl", function ($scope, $http, conf) {
});

LogViewerApp.controller("DocusignCtrl", function ($scope, $http, conf) {
});

LogViewerApp.controller("ActivityCtrl", function ($scope, $http, conf) {
});

LogViewerApp.directive('header', function () {
    var directive = {};
    directive.restrict = 'E';

    directive.templateUrl = "header.html";
    //  directive.template="Hai All.....";
    return directive;
});

LogViewerApp.directive('footer', function () {
    var directive = {};
    directive.restrict = 'E';

    directive.templateUrl = "footer.html";
    //  directive.template="Hai All.....";
    return directive;
});


LogViewerApp.directive('listview', function () {
    var directive = {};
    directive.restrict = 'E';
    directive.controller = 'LoginLogCtrl';
    alert("listview");
    return directive;
});

LogViewerApp.directive('filteruserid', function () {
    var directive = {};
   
    directive.restrict = 'E';
    directive.template = '<input type="checkbox" id="useridactivator">'+'Filter by User ID'+'<input type="text" id="userId" placeholder="Enter the User Id" ng-model="userId">';
     return directive;
});

LogViewerApp.directive('filterdate', function () {
    var directive = {};
    directive.restrict = 'E';
    directive.template = '<input type="checkbox" id="dateactivator" ng-model="dateactivator">Filter by Date<input type="date" ng-model="fromDate" id="fromDate"><input type="date" ng-model="toDate" id="toDate">';
    return directive;
});

LogViewerApp.directive('resetbtn', function () {
    var directive = {};
    directive.restrict = 'E';
    directive.template = '<input type="button" class="btn btn-warning" value="RESET" ng-click="reset();">';
    return directive;
});

LogViewerApp.directive('applybtn', function () {
    var directive = {};
    directive.restrict = 'E';
    directive.template = '<input type="button" class="btn btn-primary" value="Apply Filters and Search" ng-click="filters();">';
    return directive;
});


