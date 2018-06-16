/**
 * 
 * 
 * */

window['_ANG_MAIN_MODULE']
.controller('gameMainCtrl' , ['$scope' , 'gameServ', 'userServ' , 'teamServ' , '$mdDialog', 'playerServ', '$q' , gameMainCtrl])
.filter('frmDate' , function(){
	return function(d){
		return moment(d).format("YYYY-MM-DD");
	}
})

function gameMainCtrl($s , $gameServ, $userServ, $teamServ, $mdDialog, $playerServ, $q){
	var _fn = {
		init : function(){
			console.log('Hello');
			_fn.game.getCurrentGame()
			.then(_fn.game.getScore);
			
		},
		game : {
			getCurrentGame : function(){
				return $gameServ.getCurrentGame({
					userSeq : $s.mod.user.seq + ""
				})
				.then(function(d){
					console.log(d);
					if(d.data.RESULT_DATA.METHOD_RESULT_DATA.length <= 0){
						// 정보 없음 
					}
					else{
						$s.mod.game.info = d.data.RESULT_DATA.METHOD_RESULT_DATA[0];
					}
				});
			},
			insert : function(){
				return $gameServ.insert({
					
				})
				.then(function(d){
					console.log(d);
				} , function(d){
					
				})
			},
			getScore : function(){
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
				})
			}
		}
	}
	
	$s.mod = {
		user : {
			seq : 1,
		},
		game : {
			info : {},
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
		}
	}
	
	
	$s.evt = {
		
	}
	
	_fn.init();
}