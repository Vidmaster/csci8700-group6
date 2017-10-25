var myApp = angular.module('myApp', ['dataGrid', 'pagination', 'ngMaterial', 'ngMessages']);

myApp.controller('AppCtrl', function ($scope, $window) {
		
		$scope.data = {
			selectedIndex: 0,
			secondLocked:  true,
			thirdLabel:   "Metrics",
			bottom:        false
		};	
		$scope.next = function() {
		  $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 3) ;
		};	
		$scope.previous = function() {
		  $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
		};
		
		$scope.showStudentView = function() {
	          $window.open('http://localhost:8082/peerreview/studentview', '_blank');
	    };
    });