myApp.factory("peerreviewsFactory", function($http){
 
    var factory = {};
 
    // read all peerreviews
    factory.readPeerreviews = function(){
        return $http({
            method: 'GET',
            url: 'http://localhost:8082/api/peerreview/all'
        });
    };
     
    // create peerreview
	factory.createPeerreview = function($scope){
		return $http({
			method: 'POST',
			data: {
				'peerreviewName' : $scope.name,
				'peerreviewDescription' : $scope.description
			},
			url: 'http://localhost:8082/api/peerreview/create'
		});
	};
	 
	// read one peerreview
	factory.readOnePeerreview = function(id){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/peerreview/read_one?id=' + id
		});
	};
	 
	// update peerreview
	factory.updatePeerreview = function($scope){
	 
		return $http({
			method: 'POST',
			data: {
				'id' : $scope.id,
				'peerreviewName' : $scope.name,
				'peerreviewDescription' : $scope.description
			},
			url: 'http://localhost:8082/api/peerreview/update'
		});
	};
	 
	// delete peerreview
	factory.deletePeerreview = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: 'http://localhost:8082/api/peerreview/delete'
		});
	};
	 
	// search all peerreviews
	factory.searchPeerreviews = function(keywords){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/peerreview/search?s=' + keywords
		});
	};
     
    return factory;
});