angular.module('register', [])
.component('prRegister', {
    templateUrl: '/js/register/register.template.html',
  })
.controller('RegistrationController', function($scope, $http, $location) {
	$scope.formData = {};
	
	$scope.register = function(isValid) {
		if (!isValid) {
			alert("An error exists with the form data. Please correct it and try again");
			return;
		}
		
		if ($scope.formData.password != $scope.formData.confirmPassword) {
			$scope.success = false;
			$scope.message = "Passwords do not match";
			return;
		}
		
		$http({
			method: 'POST',
			url: '/api/users',
			data: $.param($scope.formData),
			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).then(function(data) {
		    console.log(data);
		    
		    $scope.success=data.data.success;
		    
		    if (!data.data.success) {
		      // if not successful, bind errors to error variables
		      $scope.message = data.data.message;
		    } else {
		      // if successful, bind success message to message
		    	alert(data.data.message);
		      $scope.formData = {};
		      $scope.message=null;
		      $scope.success=null;
		      $location.path('/login');
		    }
		});
		
	};
	
});