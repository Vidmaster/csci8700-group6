myApp.controller('peerreviewsController', function($scope, $mdDialog, $mdToast, peerreviewsFactory){
 
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
			.textContent('Student will be deleted.')
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