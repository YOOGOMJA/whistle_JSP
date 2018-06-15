/**
 *	사용자 관련 서비스 모음 
 */

window['_ANG_MAIN_MODULE']
.service('userServ' , ['$http' , function($http){
	var conf = {
		url : '/Whislte/API/users'
	}
	
	return {
		/*
		 * 회원가입 
		 * */
		signup : function(prms){
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
		
		/*
		 * 로그인
		 * */
		login : function(prms){
			prms = JSON.stringify(prms);
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'fn' : 'login',
					'params' : prms
				}
			});
		},
		
		/*
		 * 	로그아웃
		 * */
		logout : function(){
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'logout',
					'params' : ''
				}
			});
		}
	}
}]);
