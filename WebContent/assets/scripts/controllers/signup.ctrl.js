/**
 * 
 */

window['_ANG_MAIN_MODULE'].controller('signupCtrl', [ '$scope', '$rootScope',
		'userServ', signupCtrl ]);

function signupCtrl($s, $rs, $userServ){
	
	$s.mod = {
		email : '',
		name : '',
		password : '',
		password2 : '',
		city : 0,
		
		err : {
			msg : '',
			cd : 0
		}
	}
	
	var _fn = {
		init : function(prms){
			console.log('hello');
		},
		valid : function(){
			if($s.mod.email === ''){ return false; }
			else if($s.mod.name === ''){ return false; }
			else if($s.mod.password ===''){ return false;}
			else if($s.mod.password2 === ''){ return false; }
			else if($s.mod.password != $s.mod.password2){
				return false;
			}
			else{ return true; }
		}
	}
	
	$s.fn = {
		evt : {
			err : {
				clear : function(){
					$s.mod.err.cd = 0;
					$s.mod.err.msg=  '';
				}
			},
			submit : function(){

				$s.fn.evt.err.clear();

				if(_fn.valid()){
					$userServ.signup({
						userName : $s.mod.name,
						userMail : $s.mod.email,
						userPW : $s.mod.password,
						userPW2 : $s.mod.password2,
						userCity : 0
					})
					.then(function(d){
						if(d.data.RESULT_CD == 1){
							if(d.data.RESULT_DATA.METHOD_RESULT_CD > 0){
								alert('가입 성공!');
							}
							else{
								//alert(d.data.RESULT_DATA.METHOD_ERR_MSG);
								console.log(d.data.RESULT_DATA);
								$s.mod.err.cd = -1;
								$s.mod.err.msg = d.data.RESULT_DATA.METHOD_ERR_MSG;
							}
						}
						else{
							//alert(d.data.RESULT_DATA.METHOD_ERR_MSG);
							console.log(d.data.RESULT_DATA);
							
							$s.mod.err.cd = -2;
							$s.mod.err.msg = d.data.RESULT_DATA.METHOD_ERR_MSG;
						}
					} , function(d){
						alert('일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세');
						console.log(d);
					})
				}
				else{ 
					$s.mod.err.cd = -3;
					if($s.mod.password != $s.mod.password2){
						$s.mod.err.msg = '비밀번호가 일치하지 않습니다';
					}
					else{ $s.mod.err.msg = '입력값을 확인해주세요'; }
				}
			}
		}
	}
	
	_fn.init();
	
}


