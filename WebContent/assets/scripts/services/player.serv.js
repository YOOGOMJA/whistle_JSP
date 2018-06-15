/**
 *	선수 관련 서비스 모음 
 */

window['_ANG_MAIN_MODULE']
.service('playerServ' , ['$http' , function($http){
	var conf = {
		url : '/Whislte/API/player'
	}
	
	return {
		/*
		 * insert
		 * */
		insert : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'insert',
					'params' : prms
				}
			});
		},
		update : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'update',
					'params' : prms
				}
			});
		},
		delete : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'signup',
					'params' : prms
				}
			});
		},
		insertAtTeam : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'insertAtTeam',
					'params' : prms
				}
			});
		},
		removeFromTeam : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'removeFromTeam',
					'params' : prms
				}
			});
		},
		select : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'select',
					'params' : prms
				}
			});
		},
		selectByPriority : function(prms){
			
			//prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'selectByPriority',
					'params' : prms
				}
			})
		},
		updatePriority : function(prms){
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'updatePriority',
					'params' : prms
				}
			})
		}
	}
}]);
