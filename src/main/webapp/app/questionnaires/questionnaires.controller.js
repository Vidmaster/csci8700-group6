myAppStudentView.controller('questionnairesController', function($scope, $mdDialog, $mdToast, questionnairesFactory){
 
	// read questionnaires
    $scope.readQuestionnaires = function(){
    	
    	$scope.searchParams = {};
    	
    	$scope.ordinalOptions = [
			{label: "Strongly Disagree", value : "1"},
			{label: "Disagree", value : "2"},
			{label: "Neutral", value : "3"},
			{label: "Agree", value : "4"},
			{label: "Strongly Agree", value : "5"}
		];
    	
    	$scope.intervalOptions = [
			{label: "1", value : "1"},
			{label: "2", value : "2"},
			{label: "3", value : "3"},
			{label: "4", value : "4"},
			{label: "5", value : "5"}
		];
    	
 
        // use questionnaires factory
    	questionnairesFactory.readQuestionnaires().then(function successCallback(response){
    		console.log(response);
            $scope.questionnaires = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        });
 
    }
});