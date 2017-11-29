myAppStudentView.factory("questionnairesFactory", function($http){
 
    var factory = {};
 
    // read all questionnaires
    factory.readQuestionnaires = function(){
        return $http({
            method: 'GET',
            url: '/api/peerreview/student'
        });
    };
    
    return factory;
});