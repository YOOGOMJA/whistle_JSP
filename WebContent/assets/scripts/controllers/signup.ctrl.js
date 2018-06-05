/**
 * 
 */

window['_ANG_MAIN_MODULE'].controller('signupCtrl', [ '$scope', '$rootScope',
		'userServ', signupCtrl ]);

function signupCtrl($s, $rs, $userServ){
	
	$s.mod = {
		email : '1',
		name : '',
		password : '',
		password2 : '',
		city : 0
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
			submit : function(){
				console.log('called');
				if(_fn.valid()){
					$userServ.signup({
						userName : $s.mod.name,
						userMail : $s.mod.mail,
						userPassword : $s.mod.password,
						userPassword2 : $s.mod.password2,
						city : 0
					})
					.then(function(d){
						alert('success!');
						console.log(d);
					} , function(d){
						alert('err!');
						console.log(d);
					})
				}
				else{ alert('항목확인 !'); }
			}
		}
	}
	
	_fn.init();
	
}


