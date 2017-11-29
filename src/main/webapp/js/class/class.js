myApp
.factory('classFactory', function($http) {
	var factory = {};
	
    factory.getClassesByInstructor = function(id){
        return $http({
            method: 'GET',
            url: '/api/users/' + id + '/classes'
        });
    };
	
    factory.getClasses = function() {
    	return $http({
    		method: 'GET',
    		url: '/api/class'
    	});
    };
    
    factory.newClass = function($scope) {
    	return $http({
			method: 'POST',
			data: {
				'name' : $scope.name,
				'instructor' : $scope.user.principal
			},
			url: '/api/class'
		});
	};
    
	return factory;
})
.controller('classController', function($scope, $element, $mdDialog, $mdToast, classFactory) {
	
})