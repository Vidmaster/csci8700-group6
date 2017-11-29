myApp.factory("peerreviewsFactory", function($http){
	var getAll = '/api/peerreview'
	
	
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
            url: '/api/students'
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
				'name' : $scope.name,
				'description' : $scope.description,
				'peerreviewStudents' : $scope.selectedStudents,
				'metrics' : $scope.selectedMetrics
			},
			url: '/api/peerreview'
		});
	};
	 
	// read one peerreview
	factory.readOnePeerreview = function(id){
		return $http({
			method: 'GET',
			url: '/api/peerreview/' + id
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
			url: '/api/peerreview?s=' + keywords
		});
	};
	
	// run peerreview
	factory.runPeerreview = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: '/api/peerreview/' + id + '/run'
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