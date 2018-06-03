/**
 * 
 */

window['_ANG_MAIN_MODULE']
.controller('loginCtrl' , ['$scope' , '$rootScope' , 'userServ' , loginCtrl]);

function loginCtrl($s, $rs , $userServ){
	$s.fn = {
		init : function(){
			console.log('Hello World');
			$userServ.test().then(function(d){
				if(d.data.RESULT_CD === 1){
					console.log('SUCCESS!' , d.data);
				}
				else{
					console.log(d.data.ERR_MSG);
				}
			} , function(){
				console.log('FAILED' , arguments);
			});
			
		}
	}
	
	
	$s.fn.init();
}