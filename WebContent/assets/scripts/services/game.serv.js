/**
 *	팀 관련 서비스 모음 
 */

window['_ANG_MAIN_MODULE']
.service('gameServ' , ['$http' , function($http){
	var conf = {
		url : '/Whislte/API/game'
	}
	
	return {
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
		getCurrentGame : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'getCurrentGame',
					'params' : prms
				}
			});
		},
		insertTrack : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'insertTrack',
					'params' : prms
				}
			});
		},
		getScore :  function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'getScore',
					'params' : prms
				}
			});
		},
		
	}
}]);
