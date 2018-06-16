/**
 * 	로그인 후 메인 화면 컨트롤러 
 */


window['_ANG_MAIN_MODULE']
.controller('comMainCtrl' , ['$scope' , 'userServ' , 'teamServ' , 'gameServ', '$q' , comMainCtrl])

function comMainCtrl($s , $userServ , $teamServ , $gameServ){
	var _fn = {
		init : function(){
			console.log('Hello!');
			_fn.team.load()
			.then(_fn.game.load)
			.then(_fn.game.loadScore);
		},
		team : {
			load : function(){
				return $teamServ.select({})
				.then(function(d){
					$s.mod.team.info = d.data.RESULT_DATA.METHOD_RESULT_DATA[0];
				});
			}
		},
		game : {
			load : function(){
				return $gameServ.getCurrentGame({
					userSeq : "1"
				})
				.then(function(d){
					$s.mod.game.info = d.data.RESULT_DATA.METHOD_RESULT_DATA[0];
				});
			},
			loadScore : function(){
				console.log('hi')
				return $gameServ.getScore({
					userSeq : "1"
				})
				.then(function(d){
					$s.mod.game.score.home = d.data.RESULT_DATA.METHOD_RESULT_DATA[0].score;
					$s.mod.game.score.away = d.data.RESULT_DATA.METHOD_RESULT_DATA[1].score;
					
					$s.mod.game.timeTxt = moment($s.mod.game.info.gameStartedDT).fromNow();
					
					console.log($s.mod);
				}, function(d){
					console.log('fail' , d);
				});
			}
		}
	}
	
	$s.mod = {
		game : {
			timeTxt : "",
			info : {
				
			},
			score : {
				home : 0,
				away : 0
			},
			history :[
				{date : '2018-01-01 13:00:00.0' , homeScore : 23 , awayScore : 30 },
				{date : '2018-02-13 13:00:00.0' , homeScore : 23 , awayScore : 30 },
				{date : '2018-03-11 13:00:00.0' , homeScore : 23 , awayScore : 30 },
				{date : '2018-04-29 13:00:00.0' , homeScore : 23 , awayScore : 30 }
			]
		},
		team : {
			info : {}
		}
	};
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