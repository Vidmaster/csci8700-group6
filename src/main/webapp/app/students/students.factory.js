myApp.factory("studentsFactory", function($http){
 
    var factory = {};
 
    // read all students
    factory.readStudents = function(){
        return $http({
            method: 'GET',
            url: '/api/students'
        });
    };
     
    // create student
	factory.createStudent = function($scope){
		return $http({
			method: 'POST',
			data: {
				'studentName' : $scope.studentName,
				'studentEmailAddress' : $scope.emailAddress
			},
			url: '/api/student/create'
		});
	};
	 
	// read one student
	factory.readOneStudent = function(id){
		return $http({
			method: 'GET',
			url: '/api/users/' + id
		});
	};
	 
	// update student
	factory.updateStudent = function($scope){
	 
		return $http({
			method: 'PUT',
			data: {
				'id' : $scope.id,
				'studentName' : $scope.studentName,
				'studentEmailAddress' : $scope.emailAddress
			},
			url: '/api/users/' + $scope.id
		});
	};
	 
	// delete student
	factory.deleteStudent = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: '/api/student/delete'
		});
	};
	 
	// search all students
	factory.searchStudents = function(keywords){
		return $http({
			method: 'GET',
			url: '/api/users?username=' + keywords
		});
	};
     
    return factory;
});