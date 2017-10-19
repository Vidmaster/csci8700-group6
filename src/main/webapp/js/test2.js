var myAppStudentView = angular.module('myAppStudentView', ['dataGrid', 'pagination', 'ngMaterial', 'ngMessages']);

myAppStudentView.controller('AppStudentViewCtrl', function ($scope, $window) {
		
		$scope.data = {
			selectedIndex: 0,
			bottom:        false
		};	
});