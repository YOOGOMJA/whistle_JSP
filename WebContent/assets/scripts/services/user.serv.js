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
		}
	}
}]);
