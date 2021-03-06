'use strict';

angular.module('auth', []).factory(
    'auth',

    function($http, $location) {

      var auth = {

        authenticated : function() { console.log("authenticated"); return false; },
        user : null,

        loginPath : '/login',
        logoutPath : '/logout',
        homePath : '/',

        authenticate : function(credentials, callback) {
        	console.log('authenticating...');
          var headers = credentials && credentials.username ? {
            authorization : "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
          } : {};

          $http.get('/user', {
            headers : headers
          }).then(function(response) {
        	  console.log('/user response: ');
		    	console.log(response);
            if (response.data.name) {
              auth.authenticated = true;
              auth.user = response.data;
              $location.path(auth.homePath);
            } else {
            	console.log("not authenticated");
              auth.authenticated = false;
              auth.user = null;
              $location.path(auth.loginPath);
            }
            
            callback && callback(auth.authenticated, auth.user);
          }, function(r) {
        	  console.log('authentication failure');
        	  console.log(r);
            auth.authenticated = false;
            callback && callback(false);
          });

        },

        clear : function() {
        	  auth.authenticated = false;
        	  $location.path(auth.homePath);
        	  $http.post(auth.logoutPath, {});
        	},

        init : function(homePath, loginPath, logoutPath) {
        	  auth.homePath = homePath;
        	  auth.loginPath = loginPath;
        	  auth.logoutPath = logoutPath;
        	}

      };

      return auth;

    });