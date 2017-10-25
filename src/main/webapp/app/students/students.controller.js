myApp.controller('studentsController', function($scope, $mdDialog, $mdToast, studentsFactory){
 
    // read students
    $scope.readStudents = function(){
 
        // use students factory
    	studentsFactory.readStudents().then(function successCallback(response){
    		console.log(response);
            $scope.students = response.data;
        }, function errorCallback(response){
            $scope.showToast("Unable to read record.");
        });
 
    }
     
	 
		// show 'create student form' in dialog box
		$scope.showCreateStudentForm = showCreateStudentForm;
					
		function showCreateStudentForm(event) {
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
						'                    <input ng-model="studentName">' +
						'                </md-input-container>' +
						'                <md-input-container class="md-block">' +
						'                    <label>Email Address</label>' +
						'                    <input ng-model="emailAddress" type="email">' +
						'                </md-input-container>' +
						'            </div>' +
						'        </md-dialog-content>' +
						'        <md-dialog-actions layout="row">' +
						'            <md-button ng-click="cancel()">Cancel</md-button>' +
						'            <md-button ng-click="createStudent()" class="md-primary">Create</md-button>' +
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
	 
	// create new student
	$scope.createStudent = function(){
	 
		studentsFactory.createStudent($scope).then(function successCallback(response){
	 
			console.log(response);
			
			// tell the user new student was created
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readStudents();
	 
			// close dialog
			$scope.cancel();
	 
			// remove form values
			$scope.clearStudentForm();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to create record.");
		});
	}
	
	// retrieve record to fill out the form
	$scope.readOneStudent = function readOneStudent(id){
	 
		// get student to be edited
		studentsFactory.readOneStudent(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.studentName = response.data.studentName;
			$scope.emailAddress = response.data.studentEmailAddress;
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Read One Student</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- display student information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Name</label>' +
							'                    <input ng-model="studentName" disabled>' +
							'                </md-input-container>' +
							'                <md-input-container class="md-block">' +
							'                    <label>Email Address</label>' +
							'                    <input ng-model="emailAddress" disabled>' +
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
					$scope.clearStudentForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
	 
	// retrieve record to fill out the form
	$scope.showUpdateStudentForm = function(id){
	 
		// get student to be edited
		studentsFactory.readOneStudent(id).then(function successCallback(response){
	 
			// put the values in form
			$scope.id = response.data.id;
			$scope.studentName = response.data.studentName;
			$scope.emailAddress = response.data.studentEmailAddress;
	 
			$mdDialog.show({
				controller: DialogController,
				template:   '<md-dialog>' +
							'    <form ng-cloak>' +
							'        <!-- dialog box title -->' +
							'        <md-toolbar>' +
							'            <div class="md-toolbar-tools">' +
							'                <h2>Update Student</h2>' +
							'            </div>' +
							'        </md-toolbar>' +
							'        <md-dialog-content>' +
							'            <div class="md-dialog-content">' +
							'                <!-- dialog box input fields with editable student information -->' +
							'                <md-input-container class="md-block">' +
							'                    <label>Name</label>' +
							'                    <input ng-model="studentName">' +
							'                </md-input-container>' +
							'                <md-input-container class="md-block">' +
							'                    <label>Email Address</label>' +
							'                    <input ng-model="emailAddress" type="email">' +
							'                </md-input-container>' +
							'            </div>' +
							'        </md-dialog-content>' +
							'        <!-- dialog box buttons -->' +
							'        <md-dialog-actions layout="row">' +
							'            <md-button ng-click="cancel()">Cancel</md-button>' +
							'            <md-button ng-click="updateStudent()" class="md-primary">Update</md-button>' +
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
					$scope.clearStudentForm();
				}
			);
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to retrieve record.");
		});
	 
	}
 
	// update student record / save changes
	$scope.updateStudent = function(){
	 
		studentsFactory.updateStudent($scope).then(function successCallback(response){
	 
			// tell the user student record was updated
			$scope.showToast(response.data.message);
	 
			// refresh the student list
			$scope.readStudents();
	 
			// close dialog
			$scope.cancel();
	 
			// clear modal content
			$scope.clearStudentForm();
	 
		},
		function errorCallback(response) {
			$scope.showToast("Unable to update record.");
		});
	 
	}
 
	// cofirm student deletion
	$scope.confirmDeleteStudent = function(event, id){
	 
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
				// if user clicked 'Yes', delete student record
				$scope.deleteStudent();
			},
	 
			// 'No' button
			function() {
				// hide dialog
			}
		);
	}
	
	// delete student
	$scope.deleteStudent = function(){
	 
		studentsFactory.deleteStudent($scope.id).then(function successCallback(response){
	 
			// tell the user student was deleted
			$scope.showToast(response.data.message);
	 
			// refresh the list
			$scope.readStudents();
	 
		}, function errorCallback(response){
			$scope.showToast("Unable to delete record.");
		});
	 
	}
	 
	// search students
	$scope.searchStudents = function(){	 
		// use students factory
		studentsFactory.searchStudents($scope.student_search_keywords).then(function successCallback(response){
			$scope.students = response.data;
		}, function errorCallback(response){
			$scope.showToast("Unable to read record.");
		});
	}
	
	// clear variable / form values
	$scope.clearStudentForm = function(){
		$scope.id = "";
		$scope.studentName = "";
		$scope.emailAddress = "";
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