myApp.factory("metricsFactory", function($http){
 
    var factory = {};
 
    // read all metrics
    factory.readMetrics = function(){
        return $http({
            method: 'GET',
            url: 'http://localhost:8082/api/metric/all'
        });
    };
     
    // create metric
	factory.createMetric = function($scope){
		return $http({
			method: 'POST',
			data: {
				'peerreviewMetricDefinition' : $scope.definition,
				'peerreviewMetricType' : $scope.type
			},
			url: 'http://localhost:8082/api/metric/create'
		});
	};
	 
	// read one metric
	factory.readOneMetric = function(id){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/metric/read_one?id=' + id
		});
	};
	 
	// update metric
	factory.updateMetric = function($scope){
	 
		return $http({
			method: 'POST',
			data: {
				'id' : $scope.id,
				'peerreviewMetricDefinition' : $scope.definition,
				'peerreviewMetricType' : $scope.type
			},
			url: 'http://localhost:8082/api/metric/update'
		});
	};
	 
	// delete metric
	factory.deleteMetric = function(id){
		return $http({
			method: 'POST',
			data: { 'id' : id },
			url: 'http://localhost:8082/api/metric/delete'
		});
	};
	 
	// search all metrics
	factory.searchMetrics = function(keywords){
		return $http({
			method: 'GET',
			url: 'http://localhost:8082/api/metric/search?s=' + keywords
		});
	};
     
    return factory;
});