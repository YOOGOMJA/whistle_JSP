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
			return $http({
				method : 'GET',
				url : conf.url,
				params : {
					fn : 'refMethod'
				}
			});
		}
	}
}]);
