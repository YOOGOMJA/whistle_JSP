/**
 *	로그인 화면 컨트롤러 
 */

window['_ANG_MAIN_MODULE'].controller('loginCtrl', [ '$scope', '$rootScope',
		'userServ', loginCtrl ]);

function loginCtrl($s, $rs, $userServ) {
	var _fn = {
		init : function() {
			console.log('Hello World');
			$userServ.test().then(function(d) {
				if (d.data.RESULT_CD === 1) {
					console.log('SUCCESS!', d.data);
				} else {
					console.log(d.data.ERR_MSG);
				}
			}, function() {
				console.log('FAILED', arguments);
			});

		}
	}

	//$s.fn.init();
	
	$s.mod = {
		user :{
			mail : 'a@b.com',
			password : '1234'
		},
		err : {
			cd : 0,
			msg : ''
		}
	}
	
	$s.fn = {
		err : {
			clear : function(){
				console.log('He')
				$s.mod.err.cd = 0;
				$s.mod.err.msg = '';
			}
		},
		evt : {
			login : function(){
//				if($s.mod.user.mail === ''){return ;}
//				if($s.mod.user.password === ''){ return ; }
				$s.fn.err.clear();
				$userServ.login({
					userMail : $s.mod.user.mail,
					userPW : $s.mod.user.password
				}).then(function(d){ 
					//console.log(d);
					
					if(d.data.RESULT_CD > 0){
						if(d.data.RESULT_DATA.METHOD_RESULT_CD > 0){
							location.href='../Main.jsp';
						}
						else if(d.data.RESULT_DATA.METHOD_RESULT_CD == 0){
							$s.mod.err.cd = -99;
							$s.mod.err.msg =  '아이디 혹은 패스워드를 확인해주세요';
						}
						else{
							$s.mod.err.cd = -1;
							$s.mod.err.msg = d.data.RESULT_DATA.METHOD_ERR_MSG;
						}
					}
					else{
						$s.mod.err.cd = -2;
						$s.mod.err.msg = '입력값을 확인해주세요';
					}
				} , 
				function(d){ 
					$s.mod.err.cd = -3;
					$s.mod.err.msg = '일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세';
				})
			}
		}
	}
}