myApp.factory("peerreviewsFactory", function($http){
	var getAll = '/api/peerreview/all'
	
	
    var factory = {};
 
    // read all peerreviews
    factory.readPeerreviews = function(){
        return $http({
            method: 'GET',
            url: getAll
        });
    };
    
    // read all students
    factory.readPStudents = function(){
        return $http({
            method: 'GET',
            url: '/api/student/all'
        });
    };
    
 // read all metrics
    factory.readPMetrics = function(){
        return $http({
            method: 'GET',
            url: '/api/metric/all'
        });
    };
     
    // create peerreview
	factory.createPeerreview = function($scope){
		return $http({
			method: 'POST',
			data: {
				'peerreviewName' : $scope.name,
				'peerreviewDescription' : $scope.description,
				'peerreviewStudents' : $scope.selectedStudents.toString(),
				'peerreviewMetrics' : $scope.selectedMetrics.toString()
			},
			url: '/api/peerreview/create'
		});
	};
	 
	// read one peerreview
	factory.readOnePeerreview = function(id){
		return $http({
			method: 'GET',
			url: '/api/peerreview/read_one?id=' + id
		});
	};
	 
	// update peerreview
	factory.updatePeerreview = function($scope){
	 
		return $http({
			method: 'POST',
			data: {
				'id' : $scope.id,
				'peerreviewName' : $scope.name,
				'peerreviewDescription' : $scope.description,
				'peerreviewStudents' : $scope.selectedStudents.toString(),
				'peerreviewMetrics' : $scope.selectedMetrics.toString()
			},
			url: '/api/peerreview/update'
		});
	};
	 
	// delete peerreview
	factory.deletePeerreview = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: '/api/peerreview/delete'
		});
	};
	 
	// search all peerreviews
	factory.searchPeerreviews = function(keywords){
		return $http({
			method: 'GET',
			url: '/api/peerreview/search?s=' + keywords
		});
	};
	
	// run peerreview
	factory.runPeerreview = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: '/api/peerreview/run'
		});
	};
	
	// search all data grid
	factory.getDataGrid = function(){
		return $http({
			method: 'GET',
			url: '/api/peerreview/result'
		});
	};
     
    return factory;
});