var myAppStudentView = angular.module('myAppStudentView', ['dataGrid', 'pagination', 'ngMaterial', 'ngMessages']);

myAppStudentView.controller('AppStudentViewCtrl', function ($scope, $window) {
		
		$scope.data = {
			selectedIndex: 0,
			bottom:        false
		};
		
		$scope.auth = function() {
	          $window.open('http://localhost:8082/peerreview/professorview', '_self');
	    };
});