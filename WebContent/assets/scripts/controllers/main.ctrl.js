/**
 * 	로그인 후 메인 화면 컨트롤러 
 */

window['_ANG_MAIN_MODULE']
.controller('mainCtrl' , ['$scope', '$rootScope' , 'userServ', mainCtrl]);

function mainCtrl($s , $rs, $userServ){
	var _fn = {
		init : function(){
			console.log('Hello!');
		}
	}
	
	$s.mod = {};
	$s.fn = {
		evt : {
			logout : function(){
				$userServ.logout()
				.then(function(){
					location.href='./users/login.jsp';
				}, function(){
					alert('일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
				});
			}
		}
	}
	
	_fn.init();
}