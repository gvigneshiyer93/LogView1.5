LogViewerApp
        .directive('listView', function ($http) {
            return {
                restrict: 'E',
                transclude: true,
                scope: {
                    contacts: "=",
                    filterJson: "="
                },
                link: function (scope, element, attrs) {
                   
                },
                templateUrl: 'dire_listView.html'
            };
        })
        .directive('filters', function () {
            return {
                restrict: 'E',
                scope: {
                    filterJson: "@"
                },
                link: function (scope, element, attrs) {
                    console.log(scope);
                },
                templateUrl: 'dire_filters.html'
            };
        });