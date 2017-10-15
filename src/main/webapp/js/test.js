//var app = angular.module('myAppProd', ['ngMaterial']);
var myApp = angular.module('myApp', ['dataGrid', 'pagination', 'ngMaterial', 'ngMessages']);

myApp.controller('AppCtrl', ['$scope', 'myAppFactory', function ($scope, myAppFactory) {
		
		$scope.data = {
			selectedIndex: 0,
			secondLocked:  true,
			thirdLabel:   "Metrics",
			bottom:        false
		};	
		$scope.next = function() {
		  $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 2) ;
		};	
		$scope.previous = function() {
		  $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
		};

		
		
        $scope.gridOptions = {
            data: [],
            urlSync: false
        };
        myAppFactory.getData().then(function (responseData) {
            $scope.gridOptions.data = responseData.data;
        })

    }])
    .factory('myAppFactory', function ($http) {
        return {
            getData: function () {
                return $http({
                    method: 'GET',
                    url: 'http://localhost:8082/api/peerreview/all'
                });
            }
        }
    });