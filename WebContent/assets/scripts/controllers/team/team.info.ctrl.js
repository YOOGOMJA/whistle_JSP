/**
 * 팀의 메인 컨트롤러
 */

window['_ANG_MAIN_MODULE']
.controller('teamInfoCtrl' , ['$scope' , 'userServ' , 'teamServ' , '$mdDialog', 'playerServ', '$q' , teamInfoCtrl])
.filter('position' , function(){
	return function(d){
		d = Number(d);
		if(d === 0){ return 'PG'; }
		else if(d === 1){ return 'SG'; }
		else if(d === 2){ return 'SF'; }
		else if(d === 3){ return 'PF'; }
		else if(d === 4){ return 'C'; }
		else if(d === 5){ return 'G'; }
		else if(d === 6){ return 'F'; }
		else{return '';}
		
	}
});

function teamInfoCtrl($s , $userServ, $teamServ, $mdDialog, $playerServ, $q){
	var _fn = {
		init : function(){
			$teamServ.select({})
			.then(function(d){
				//$s.mod.team.info = d.data.RESULT_DATA.METHOD_RESULT_DATA;
				
				if(d.data.RESULT_DATA.METHOD_RESULT_DATA.length > 0){
					$s.mod.team.info = d.data.RESULT_DATA.METHOD_RESULT_DATA[0];
				}
				else{
					return $q.reject();
				}
			} , function(d){ console.log('fail' , d); })
			.then(_fn.player.load.starter)
			.then(_fn.player.load.bench)
		},
		player : {
			load : {
				all : function(){
					return $playerServ.selectByPriority({
						'teamSeq' : $s.mod.team.info.teamSeq,
						type : 0	
					}).
					then(function(d){
						$s.mod.player.list = d.data.RESULT_DATA.RESULT_METHOD_DATA;
						
						$s.mod.player.list.map(function(item){
							console.log(item);
						})
					});
				},
				starter : function(){
					return $playerServ.selectByPriority({
						'teamSeq' : $s.mod.team.info.teamSeq,
						type : 1	
					}).
					then(function(d){
						$s.mod.player.starter = d.data.RESULT_DATA.RESULT_METHOD_DATA;
						
						$s.mod.player.list.map(function(item){
							console.log(item);
						})
					});
				},
				bench : function(){
					return $playerServ.selectByPriority({
						'teamSeq' : $s.mod.team.info.teamSeq,
						type : 2	
					}).
					then(function(d){
						$s.mod.player.bench = d.data.RESULT_DATA.RESULT_METHOD_DATA;
						
						$s.mod.player.list.map(function(item){
							console.log(item);
						})
					});
				}
			}
		}
	}
	
	$s.mod = {
		team : {
			info : {
				teamSeq : -1
			}
		},
		player : {
			list : [],
			starter : [],
			bench : []
		}
	}
	
	
	$s.evt = {
		team : {
			create: function(){
				location.href='./insert.jsp';
			}
		},
		plyr : {
			insert : function(ev){
			$mdDialog.show({
			      controller: dialogCtrl,
			      templateUrl: '/Whislte/assets/templates/insertPlayer.tpl.html',
			      parent: angular.element(document.body),
			      targetEvent: ev,
			      clickOutsideToClose:true,
//			      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
			    })
			    .then(function(data) {
			      console.log('insert' , data);
			      return $playerServ.insertAtTeam({
			    	  	teamSeq : $s.mod.team.info.teamSeq,
			    	  	name : data.name,
			    	  	backNumber : data.backNumber + '',
			    	  	position : data.position + ''
			      })
			      .then(function(){ alert('추가 되었습니다!'); });
			    }, function() {
			      console.log('close');
			    }).then(_fn.player.load.bench);
			},
			movToBench : function(item){
				$playerServ.updatePriority({
					teamSeq : item.plyrTeamSeq,
					plyrSeq : item.plyrSeq,
					type : 'BENCH'
				}).
				then(function(d){
					console.log('success', d);
				} , function(){
					console.log('fail');
				})
				.then(_fn.player.load.starter)
				.then(_fn.player.load.bench);
			},
			movToStart : function(item){
				if($s.mod.player.starter.length >= 5){
					alert('선발 목록이 가득 찼습니다');
					return;
				}
				$playerServ.updatePriority({
					teamSeq : item.plyrTeamSeq,
					plyrSeq : item.plyrSeq,
					type : 'STARTER'
				}).
				then(function(d){
					console.log('success', d);
				} , function(){
					console.log('fail');
				})
				.then(_fn.player.load.starter)
				.then(_fn.player.load.bench);
			}
		}
	}
	
	function dialogCtrl($scope, $mdDialog) {
		console.log('Hello');
        $scope.hide = function() {
        $mdDialog.hide();
       };
       $scope.player = {
    		   name : '',
    		   backNumber : 0,
    		   position : 0
       };
	      $scope.cancel = function() {
	        $mdDialog.cancel();
	      };

	      $scope.answer = function() {
	        $mdDialog.hide($scope.player);
	      };
	    }
	
	_fn.init();
}