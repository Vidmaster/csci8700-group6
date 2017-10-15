myApp.factory("studentsFactory", function($http){
 
    var factory = {};
 
    // read all students
    factory.readStudents = function(){
        return $http({
            method: 'GET',
            url: 'http://localhost:8082/api/student/all'
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
			url: 'http://localhost:8082/api/student/create'
		});
	};
	 
	// read one student
	factory.readOneStudent = function(id){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/student/read_one?id=' + id
		});
	};
	 
	// update student
	factory.updateStudent = function($scope){
	 
		return $http({
			method: 'POST',
			data: {
				'id' : $scope.id,
				'studentName' : $scope.studentName,
				'studentEmailAddress' : $scope.emailAddress
			},
			url: 'http://localhost:8082/api/student/update'
		});
	};
	 
	// delete student
	factory.deleteStudent = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: 'http://localhost:8082/api/student/delete'
		});
	};
	 
	// search all students
	factory.searchStudents = function(keywords){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/student/search?s=' + keywords
		});
	};
     
    return factory;
});