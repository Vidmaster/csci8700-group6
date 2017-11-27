'use strict';

angular.module('peerReviewApp', 
		[
		 'prIndex',
		 'myApp',
		 'login',
		 'register',
		 'ngRoute',
		 'auth'
		 ])
		 .config(['$locationProvider', '$routeProvider', '$httpProvider',
		    	  function config($locationProvider, $routeProvider, $httpProvider) {
				$locationProvider.hashPrefix('!');
				
				$routeProvider.when('/', {
					template: '<pr-index></pr-index>',
				}).
				when('/register', {
					controller: 'RegistrationController',
					template: '<pr-register></pr-register>',
					controllerAs: 'rc'
				}).
				when('/login', {
					template: '<pr-login></pr-login>'
				}).
				otherwise('/');
				
				$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			}
		])
	.controller('app', function($scope, $window, auth) {
		console.log('instantiated app controller');
		if (auth && auth.user) $scope.user = auth.user;
		
		$scope.logout = function() {
			console.log('logout called');
			auth.clear();
			$scope.user = null;
			$scope.authenticated = false;
			$window.location.href="/";
		};
		
		auth.authenticate(null,function() {
			console.log('app controller');
			console.log(auth)
			$scope.user = auth.user;
			$scope.authenticated = auth.authenticated;
		});
	})
	.run(function(auth) {
	    auth.init('/', '/login', '/logout');
	})
	.directive("compareTo", function() {
		return {
	        require: "ngModel",
	        scope: {
	            otherModelValue: "=compareTo"
	        },
	        link: function(scope, element, attributes, ngModel) {
	             
	            ngModel.$validators.compareTo = function(modelValue) {
	                return modelValue == scope.otherModelValue;
	            };
	 
	            scope.$watch("otherModelValue", function() {
	                ngModel.$validate();
	            });
	        }
	    };
	});