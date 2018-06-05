/**
 * 
 */

window['_ANG_MAIN_MODULE']
.service('userServ' , ['$http' , function($http){
	var conf = {
		url : '/Whislte/API/users'
	}
	
	return {
		test : function(){
			
			var prms = {
				'a' : 1,
				'b' : 2
			}
			
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					'fn' : 'get',
					'params' : JSON.stringify(prms)
				}
			});
		},
		
		/*
		 * 회원가입 
		 * 
		 * */
		signup : function(prms){
			return $http({
				method : 'POST',
				url : conf.url,
				params : {
					'userName' : prms.userName,
					'userMail' : prms.userMail,
					'userPW' : prms.userPW,
					'userPW2' : prms.userPW2,
					'userCity' : prms.userCity ? 0 : prms.userCity
				}
			});
		}
	}
}]);
