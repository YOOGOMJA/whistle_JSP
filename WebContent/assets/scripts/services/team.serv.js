/**
 *	팀 관련 서비스 모음 
 */

window['_ANG_MAIN_MODULE']
.service('teamServ' , ['$http' , function($http){
	var conf = {
		url : '/Whislte/API/team'
	}
	
	return {
		/*
		 * create
		 * */
		create : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'create',
					'params' : prms
				}
			});
		},
		/*
		 * modify
		 * */
		modify : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'modify',
					'params' : prms
				}
			});
		},
		/*
		 * set Team Manager
		 * */
		setManager : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'setManager',
					'params' : prms
				}
			});
		},
		/*
		 * Team Delete
		 * */
		delete : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'delete',
					'params' : prms
				}
			});
		},
		/*
		 * Team Info select 
		 * */
		select : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'info',
					'params' : prms
				}
			});
		}
	}
}]);
