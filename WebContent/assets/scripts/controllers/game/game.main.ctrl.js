/**
 * 
 * 
 * */

window['_ANG_MAIN_MODULE']
.controller('gameMainCtrl' , ['$scope' , 'userServ' , 'teamServ' , '$mdDialog', 'playerServ', '$q' , gameMainCtrl])


function gameMainCtrl($s , $userServ, $teamServ, $mdDialog, $playerServ, $q){
	var _fn = {
		init : function(){
			console.log('Hello')
		}
	}
	
	$s.mod = {
		game : {
			
		}
	}
	
	
	$s.evt = {
		
	}
	
	_fn.init();
}