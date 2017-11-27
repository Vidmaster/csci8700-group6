angular.
  module('prIndex', [])
  .component('prIndex', {
    templateUrl: '/js/index/index.template.html'
  })
  .controller('index', function($scope, $rootScope, $http, $location, auth, $window) {
	  var self = this;
	  var student = auth.user.principal.userRole == 'STUDENT';
	  $scope.student = student;
	  console.log(auth.user.principal.userRole);
	  console.log(student);
  });
