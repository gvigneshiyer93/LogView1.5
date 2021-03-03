LogViewerApp.controller("LoginLogListViewCtrl", function ($scope, $http) {
    $scope.contacts = [];
    $scope.filterJson = {
        gender: 'male',
        company: 'ust'
    };
    
    $http
            .get("../dummy.json")
            .then(function (response) {
                console.log(response);
                $scope.contacts = response.data;
            }, function (error) {
                console.error(error);
            });
});