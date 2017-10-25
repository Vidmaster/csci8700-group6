myApp.controller('peerreviewResultController', function($scope, $element, $mdDialog, $mdToast, peerreviewsFactory){
 
	$scope.gridOptions = {
            data: [],
            urlSync: false
        };
	
	peerreviewsFactory.getDataGrid().then(function successCallback(response){
		console.log("0-hereeeeeeeeeeeeeeeeeeeeeeeee");
		console.log(response);
		console.log("1-hereeeeeeeeeeeeeeeeeeeeeeeee");
		console.log(response.data);
		
        $scope.gridOptions.data = response.data;
    }, function errorCallback(response){
        $scope.showToast("Unable to read record.");
    });
	
});