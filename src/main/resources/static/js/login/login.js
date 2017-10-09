angular.module('login', [])
.component('prLogin', {
    templateUrl: '/js/login/login.template.html',
  })
.controller('login', function($rootScope, $http, $location, auth, $window) {
	  var self = this;

	  auth.authenticate();
	  self.credentials = {};
	  self.login = function() {
		  self.dataLoading = true;
		  console.log('called login');
		  auth.authenticate(self.credentials, function() {
		        if (auth.authenticated) {
			          //$location.path("/");
		        	  $window.location.href = "/";
			          self.authenticated = true;
			          self.error = false;
			        } else {
			          $location.path("/login");
			          self.error = true;
			        }
	        self.dataLoading = false;
	      });
	  };
	  
	  self.logout = function() {
		  console.log('called logout');
		  auth.logout();
		};
});