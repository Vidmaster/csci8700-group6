myApp.controller('peerreviewsController', function($scope, $element, $mdDialog, $mdToast, peerreviewsFactory){
	
	// read students for pr
    $scope.readPStudents = function(){ 
        // use peerreviews factory
    	peerreviewsFactory.readPStudents().then(function successCallback(response){
    		console.log(response);
            $scope.pstudents = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        }); 
    }
    
    // read metrics for pr
    $scope.readPMetrics = function(){ 
        // use peerreviews factory
    	peerreviewsFactory.readPMetrics().then(function successCallback(response){
    		console.log(response);
            $scope.pmetrics = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        }); 
    }
	
    // The md-select directive eats keydown events for some quick select
    // logic. Since we have a search input here, we don't need that logic.
    $element.find('input').on('keydown', function(ev) {
        ev.stopPropagation();
    });
 
    // read peerreviews
    $scope.readPeerreviews = function(){
 
        // use peerreviews factory
    	peerreviewsFactory.readPeerreviews().then(function successCallback(response){
    		console.log(response);
            $scope.peerreviews = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        });
 
    }
     
	 
		// show 'create peerreview form' in dialog box
		$scope.showCreatePeerreviewForm = showCreatePeerreviewForm;
					
		function showCreatePeerreviewForm(event) {
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
				'                    <label>Name</label>' +
				'                    <input ng-model="name">' +
				'                </md-input-container>' +
				'                <md-input-container class="md-block">' +
				'                    <label>Description</label>' +
				'                    <input ng-model="description">' +
				'                </md-input-container>' +
				'				<div class="md-padding" ng-cloak>' +
				'						<div layout="row">' +
				'						  <md-input-container>' +
				'							<label>Students</label>' +
				'							<md-select ng-init="readPStudents()" ng-model="selectedStudents" data-md-container-class="selectdemoSelectHeader" multiple>' +
				'							  <md-optgroup label="students">' +
				'								<md-option ng-value="student.id" ng-repeat="student in pstudents">{{student.studentName}}</md-option>' +
				'							  </md-optgroup>' +
				'							</md-select>' +
				'						  </md-input-container>' +
				'						</div>' +
				'						<div layout="row">' +
				'						  <md-input-container>' +
				'							<label>Metrics</label>' +
				'							<md-select ng-init="readPMetrics()" ng-model="selectedMetrics" data-md-container-class="selectdemoSelectHeader" multiple>' +
				'							  <md-optgroup label="metrics">' +
				'								<md-option ng-value="metric.id" ng-repeat="metric in pmetrics">{{metric.peerreviewMetricDefinition}}</md-option>' +
				'							  </md-optgroup>' +
				'							</md-select>' +
				'						  </md-input-container>' +
				'						</div>' +
				'				</div>' +
				'            </div>' +
				'        </md-dialog-content>' +
				'        <md-dialog-actions layout="row">' +
				'            <md-button ng-click="cancel()">Cancel</md-button>' +
				'            <md-button ng-click="createPeerreview()" class="md-primary">Create</md-button>' +
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
	 
	// create new peerreview
	$scope.createPeerreview = function(){
	 
		peerreviewsFactory.createPeerreview($scope).then(function successCallback(response){
	 
			console.log(response);
			
			// tell the user new peerreview was created
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readPeerreviews();
	 
			// close dialog
			$scope.cancel();
	 
			// remove form values
			$scope.clearPeerreviewForm();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to create record.");
		});
	}
	
	// retrieve record to fill out the form
	$scope.readOnePeerreview = function readOnePeerreview(id){
	 
		// get peerreview to be edited
		peerreviewsFactory.readOnePeerreview(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.name = response.data.peerreviewName;
			$scope.description = response.data.peerreviewDescription;
			$scope.selectedStudents = response.data.peerreviewStudents.split(",");
			$scope.selectedMetrics = response.data.peerreviewMetrics.split(",");
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Read One Peerreview</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- display peerreview information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Name</label>' +
							'                    <input ng-model="name" disabled>' +
							'                </md-input-container>' +
							'                <md-input-container class="md-block">' +
							'                    <label>Description</label>' +
							'                    <input ng-model="description" disabled>' +
							'                </md-input-container>' +
							'				 <div class="md-padding" ng-cloak>' +
							'						<div layout="row">' +
							'						  <md-input-container>' +
							'							<label>Students</label>' +
							'							<md-select ng-disabled="true" ng-init="readPStudents()" ng-model="selectedStudents" data-md-container-class="selectdemoSelectHeader" multiple>' +
							'							  <md-optgroup label="students">' +
							'								<md-option ng-value="student.id" ng-repeat="student in pstudents">{{student.studentName}}</md-option>' +
							'							  </md-optgroup>' +
							'							</md-select>' +
							'						  </md-input-container>' +
							'						</div>' +
							'						<div layout="row">' +
							'						  <md-input-container>' +
							'							<label>Metrics</label>' +
							'							<md-select ng-disabled="true" ng-init="readPMetrics()" ng-model="selectedMetrics" data-md-container-class="selectdemoSelectHeader" multiple>' +
							'							  <md-optgroup label="metrics">' +
							'								<md-option ng-value="metric.id" ng-repeat="metric in pmetrics">{{metric.peerreviewMetricDefinition}}</md-option>' +
							'							  </md-optgroup>' +
							'							</md-select>' +
							'						  </md-input-container>' +
							'						</div>' +
							'				 </div>' +
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
					$scope.clearPeerreviewForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
	 
	// retrieve record to fill out the form
	$scope.showUpdatePeerreviewForm = function(id){
	 
		// get peerreview to be edited
		peerreviewsFactory.readOnePeerreview(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.name = response.data.peerreviewName;
			$scope.description = response.data.peerreviewDescription;
			$scope.selectedStudents = response.data.peerreviewStudents.split(",");
			$scope.selectedMetrics = response.data.peerreviewMetrics.split(",");
			
			console.log($scope.selectedStudents);
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Update Peerreview</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- dialog box input fields with editable peerreview information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Name</label>' +
							'                    <input ng-model="name">' +
							'                </md-input-container>' +
							'                <md-input-container class="md-block">' +
							'                    <label>Description</label>' +
							'                    <input ng-model="description">' +
							'                </md-input-container>' +
							'				 <div class="md-padding" ng-cloak>' +
							'						<div layout="row">' +
							'						  <md-input-container>' +
							'							<label>Students</label>' +
							'							<md-select ng-init="readPStudents()" ng-model="selectedStudents" data-md-container-class="selectdemoSelectHeader" multiple>' +
							'							  <md-optgroup label="students">' +
							'								<md-option ng-value="student.id" ng-repeat="student in pstudents">{{student.studentName}}</md-option>' +
							'							  </md-optgroup>' +
							'							</md-select>' +
							'						  </md-input-container>' +
							'						</div>' +
							'						<div layout="row">' +
							'						  <md-input-container>' +
							'							<label>Metrics</label>' +
							'							<md-select ng-init="readPMetrics()" ng-model="selectedMetrics" data-md-container-class="selectdemoSelectHeader" multiple>' +
							'							  <md-optgroup label="metrics">' +
							'								<md-option ng-value="metric.id" ng-repeat="metric in pmetrics">{{metric.peerreviewMetricDefinition}}</md-option>' +
							'							  </md-optgroup>' +
							'							</md-select>' +
							'						  </md-input-container>' +
							'						</div>' +
							'				 </div>' +
							'            </div>' +
							'        </md-dialog-content>' +
							'        <!-- dialog box buttons -->' +
							'        <md-dialog-actions layout="row">' +
							'            <md-button ng-click="cancel()">Cancel</md-button>' +
							'            <md-button ng-click="updatePeerreview()" class="md-primary">Update</md-button>' +
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
					$scope.clearPeerreviewForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
 
	// update peerreview record / save changes
	$scope.updatePeerreview = function(){
	 
		peerreviewsFactory.updatePeerreview($scope).then(function successCallback(response){
	 
			// tell the user peerreview record was updated
			$scope.showToast(response.data.message);
	 
			// refresh the peerreview list
			$scope.readPeerreviews();
	 
			// close dialog
			$scope.cancel();
	 
			// clear modal content
			$scope.clearPeerreviewForm();
	 
		},
		function errorCallback(response) {
			$scope.showToast("Unable to update record.");
		});
	 
	}
 
	// cofirm peerreview deletion
	$scope.confirmDeletePeerreview = function(event, id){
	 
		// set id of record to delete
		$scope.id = id;
	 
		// dialog settings
		var confirm = $mdDialog.confirm()
			.title('Are you sure?')
			.textContent('Peer Review will be deleted.')
			.targetEvent(event)
			.ok('Yes')
			.cancel('No');
	 
		// show dialog
		$mdDialog.show(confirm).then(
			// 'Yes' button
			function() {
				// if user clicked 'Yes', delete peerreview record
				$scope.deletePeerreview();
			},
	 
			// 'No' button
			function() {
				// hide dialog
			}
		);
	}
	
	// delete peerreview
	$scope.deletePeerreview = function(){
	 
		peerreviewsFactory.deletePeerreview($scope.id).then(function successCallback(response){
	 
			// tell the user peerreview was deleted
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readPeerreviews();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to delete record.");
		});
	 
	}
	 
	// search peerreviews
	$scope.searchPeerreviews = function(){	 
		// use peerreviews factory
		peerreviewsFactory.searchPeerreviews($scope.peerreview_search_keywords).then(function successCallback(response){
			$scope.peerreviews = response.data;
		}, function errorCallback(response){
			$scope.showToast("Unable to read record.");
		});
	}
	
	// clear variable / form values
	$scope.clearPeerreviewForm = function(){
		$scope.id = "";
		$scope.name = "";
		$scope.description = "";
		$scope.selectedStudents = "";
		$scope.selectedMetrics = "";
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
	
	// run one peerreview
	$scope.confirmRunPeerreview = function(event, id){
		 
		// set id of record to delete
		$scope.id = id;
	 
		// dialog settings
		var confirm = $mdDialog.confirm()
			.title('Are you sure?')
			.textContent('Peer review will be launched.')
			.targetEvent(event)
			.ok('Yes')
			.cancel('No');
	 
		// show dialog
		$mdDialog.show(confirm).then(
			// 'Yes' button
			function() {
				// if user clicked 'Yes', run peerreview record
				$scope.runPeerreview();
			},
	 
			// 'No' button
			function() {
				// hide dialog
			}
		);
	}
	
	// run peerreview
	$scope.runPeerreview = function(){
	 
		peerreviewsFactory.runPeerreview($scope.id).then(function successCallback(response){
	 
			// tell the user peerreview was deleted
			$scope.showToast(response.data.message);
	 
			// refresh the list
			//$scope.readPeerreviews();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to run peer review.");
		});
	 
	}
});