/**
 * 
 */

window['_ANG_MAIN_MODULE']
.controller('menuCtrl' , ['$scope', '$rootScope' , '$mdSidenav', 'userServ' , '$mdDialog', menuCtrl]);

function menuCtrl($s , $rs, $mdSidenav,  $userServ , $mdDialog){
	
	$s.mod = {
		slide : {
			toggle : false
		},
		links : [
			{ title : 'Home' , link : '/Whislte/Main.jsp' },
			{ title : 'Make a Game' , link : '/Whislte/game/main.jsp' },
			{ title : 'Team Info' , link : '/Whislte/team/info.jsp' },
			{ title : 'Game History' , link : '/Whislte/game/main.jsp' },
			{ title : 'Statistics' , link : '/Whislte/game/main.jsp' },
		]
	}
	
	$s.fn = {
		evt : {
			toggle : function(){
				$mdSidenav('closeEventsDisabled').toggle();
			},
			go : function(link){
				location.href = link;
			},
			logout : function(){
				
				var _confirm = $mdDialog.confirm()
			        .title('Logout')
			        .textContent('로그아웃 하시겠습니까?')			        
			        .ok('logout')
			        .cancel('cancel')
			        .targetEvent(event);
				$mdDialog.show(_confirm)
				.then(function(){
					$userServ.logout({})
					.then(function(d){
						location.href='/Whislte/users/login.jsp';
					}, function(d){
						console.log('오류!');
						
						location.reload();
					})
				})
			}
		}
	}
}