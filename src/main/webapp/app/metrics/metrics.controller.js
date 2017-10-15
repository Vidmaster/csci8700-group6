myApp.controller('metricsController', function($scope, $mdDialog, $mdToast, metricsFactory){
 
	// read metrics
    $scope.readMetrics = function(){
 
        // use metrics factory
    	metricsFactory.readMetrics().then(function successCallback(response){
    		console.log(response);
            $scope.metrics = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        });
 
    }
     
	 
		// show 'create metric form' in dialog box
		$scope.showCreateMetricForm = showCreateMetricForm;
					
		function showCreateMetricForm(event) {
			$scope.type = "ordinal";
			
			$mdDialog.show({
			controller: DialogController,
			template:
						'<md-dialog>' +
						'    <form ng-cloak>' + 
						'        <md-toolbar>' +
						'            <div class="md-toolbar-tools">' +
						'                <h2>Create Peer Review</h2>' +
						'            </div>' +
						'        </md-toolbar>' +
						'        <md-dialog-content>' +
						'            <div class="md-dialog-content">' +
						'                <md-input-container class="md-block">' +
						'                    <label>Definition</label>' +
						'                    <input ng-model="definition">' +
						'                </md-input-container>' +
						'				     <p>Type:</p>' +
						'				     <md-radio-group layout="row" ng-model="type">' +
						'				 	  <md-radio-button value="ordinal"> Ordinal </md-radio-button>' +
						'				 	  <md-radio-button value="interval"> Interval </md-radio-button>' +
						'				 	  <md-radio-button value="comment"> Comment </md-radio-button>' +
						'				 	</md-radio-group>' +
						'            </div>' +
						'        </md-dialog-content>' +
						'        <md-dialog-actions layout="row">' +
						'            <md-button ng-click="cancel()">Cancel</md-button>' +
						'            <md-button ng-click="createMetric()" class="md-primary">Create</md-button>' +
						'        </md-dialog-actions>' +
						'    </form>' +
						'</md-dialog>',
			parent: angular.element(document.body),
			clickOutsideToClose: true,
			scope: $scope,
			preserveScope: true,
			fullscreen: true // Only for -xs, -sm breakpoints.
			});			  
		}
	 
	// create new metric
	$scope.createMetric = function(){
	 
		metricsFactory.createMetric($scope).then(function successCallback(response){
	 
			console.log(response);
			
			// tell the user new metric was created
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readMetrics();
	 
			// close dialog
			$scope.cancel();
	 
			// remove form values
			$scope.clearMetricForm();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to create record.");
		});
	}
	
	// retrieve record to fill out the form
	$scope.readOneMetric = function readOneMetric(id){
	 
		// get metric to be edited
		metricsFactory.readOneMetric(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.definition = response.data.peerreviewMetricDefinition;
			$scope.type = response.data.peerreviewMetricType;
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Read One Metric</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- display metric information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Definition</label>' +
							'                    <input ng-model="definition" disabled>' +
							'                </md-input-container>' +
							'				     <p>Type:</p>' +
							'				     <md-radio-group layout="row" ng-model="type">' +
							'				 	  <md-radio-button value="ordinal" ng-disabled="true"> Ordinal </md-radio-button>' +
							'				 	  <md-radio-button value="interval" ng-disabled="true"> Interval </md-radio-button>' +
							'				 	  <md-radio-button value="comment" ng-disabled="true"> Comment </md-radio-button>' +
							'				 	</md-radio-group>' +
							'            </div>' +
							'        </md-dialog-content>' +
							'        <!-- button to close the dialog box -->' +
							'        <md-dialog-actions layout="row">' +
							'            <md-button ng-click="cancel()">Cancel</md-button>' +
							'        </md-dialog-actions>' +
							'    </form>' +
							'</md-dialog>',
				parent: angular.element(document.body),
				clickOutsideToClose: true,
				scope: $scope,
				preserveScope: true,
				fullscreen: true
			}).then(
				function(){},
	 
				// user clicked 'Cancel'
				function() {
					// clear modal content
					$scope.clearMetricForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
	 
	// retrieve record to fill out the form
	$scope.showUpdateMetricForm = function(id){
	 
		// get Metric to be edited
		metricsFactory.readOneMetric(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.definition = response.data.peerreviewMetricDefinition;
			$scope.type = response.data.peerreviewMetricType;
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Update Metric</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- dialog box input fields with editable metric information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Definition</label>' +
							'                    <input ng-model="definition">' +
							'                </md-input-container>' +
							'				     <p>Type:</p>' +
							'				     <md-radio-group layout="row" ng-model="type">' +
							'				 	  <md-radio-button value="ordinal"> Ordinal </md-radio-button>' +
							'				 	  <md-radio-button value="interval"> Interval </md-radio-button>' +
							'				 	  <md-radio-button value="comment"> Comment </md-radio-button>' +
							'				 	</md-radio-group>' +
							'            </div>' +
							'        </md-dialog-content>' +
							'        <!-- dialog box buttons -->' +
							'        <md-dialog-actions layout="row">' +
							'            <md-button ng-click="cancel()">Cancel</md-button>' +
							'            <md-button ng-click="updateMetric()" class="md-primary">Update</md-button>' +
							'        </md-dialog-actions>' +
							'    </form>' +
							'</md-dialog>',
				parent: angular.element(document.body),
				targetEvent: event,
				clickOutsideToClose: true,
				scope: $scope,
				preserveScope: true,
				fullscreen: true
			}).then(
				function(){},
	 
				// user clicked 'Cancel'
				function() {
					// clear modal content
					$scope.clearMetricForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
 
	// update metric record / save changes
	$scope.updateMetric = function(){
	 
		metricsFactory.updateMetric($scope).then(function successCallback(response){
	 
			// tell the user metric record was updated
			$scope.showToast(response.data.message);
	 
			// refresh the metric list
			$scope.readMetrics();
	 
			// close dialog
			$scope.cancel();
	 
			// clear modal content
			$scope.clearMetricForm();
	 
		},
		function errorCallback(response) {
			$scope.showToast("Unable to update record.");
		});
	 
	}
 
	// cofirm metric deletion
	$scope.confirmDeleteMetric = function(event, id){
	 
		// set id of record to delete
		$scope.id = id;
	 
		// dialog settings
		var confirm = $mdDialog.confirm()
			.title('Are you sure?')
			.textContent('Student will be deleted.')
			.targetEvent(event)
			.ok('Yes')
			.cancel('No');
	 
		// show dialog
		$mdDialog.show(confirm).then(
			// 'Yes' button
			function() {
				// if user clicked 'Yes', delete metric record
				$scope.deleteMetric();
			},
	 
			// 'No' button
			function() {
				// hide dialog
			}
		);
	}
	
	// delete metric
	$scope.deleteMetric = function(){
	 
		metricsFactory.deleteMetric($scope.id).then(function successCallback(response){
	 
			// tell the user metric was deleted
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readMetrics();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to delete record.");
		});
	 
	}
	 
	// search metrics
	$scope.searchMetrics = function(){	 
		// use metrics factory
		metricsFactory.searchMetrics($scope.metric_search_keywords).then(function successCallback(response){
			$scope.metrics = response.data;
		}, function errorCallback(response){
			$scope.showToast("Unable to read record.");
		});
	}
	
	// clear variable / form values
	$scope.clearMetricForm = function(){
		$scope.id = "";
		$scope.definition = "";
		$scope.type = "ordinal";
	}
	
	// show toast message
	$scope.showToast = function(message){
		$mdToast.show(
			$mdToast.simple()
				.textContent(message)
				.hideDelay(3000)
				.position("top right")
		);
	}
     
    // methods for dialog box
	function DialogController($scope, $mdDialog) {
		$scope.cancel = function() {
			$mdDialog.cancel();
		};
	}
});