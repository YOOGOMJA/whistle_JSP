/**
 * 팀의 메인 컨트롤러
 */

window['_ANG_MAIN_MODULE']
.controller('teamInsertCtrl' , ['$scope' , 'userServ' , 'teamServ' , 'playerServ' , teamInsertCtrl])
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

function teamInsertCtrl($s , $userServ, $teamServ, $playerServ){
	var _fn = {
		init : function(){
			$teamServ.select({})
			.then(function(d){
				//$s.mod.team.info = d.data.RESULT_DATA.METHOD_RESULT_DATA;
				if(d.data.RESULT_DATA.METHOD_RESULT_DATA.length > 0){
					alert('이미 팀이 존재합니다');
					location.href='./info.jsp';
				}
			} , function(d){ console.log('fail' , d); });
		}
	}
	
	$s.mod = {
		teamName : '',
		teamCity : 0,
		cities : [
			{ CD_VALUES : 0 , CD_DESCRIPTION : '서울' },
			{ CD_VALUES : 1 , CD_DESCRIPTION : '부산' },
			{ CD_VALUES : 2 , CD_DESCRIPTION : '대구' },
			{ CD_VALUES : 3 , CD_DESCRIPTION : '인천' },
			{ CD_VALUES : 4 , CD_DESCRIPTION : '광주' },
			{ CD_VALUES : 5 , CD_DESCRIPTION : '대전' },
			{ CD_VALUES : 6 , CD_DESCRIPTION : '울산' },
			{ CD_VALUES : 7 , CD_DESCRIPTION : '세종' },
			{ CD_VALUES : 8 , CD_DESCRIPTION : '경기' },
			{ CD_VALUES : 9 , CD_DESCRIPTION : '강원' },
			{ CD_VALUES : 10 , CD_DESCRIPTION : '충북' },
			{ CD_VALUES : 11 , CD_DESCRIPTION : '충남' },
			{ CD_VALUES : 12 , CD_DESCRIPTION : '전북' },
			{ CD_VALUES : 13 , CD_DESCRIPTION : '전남' },
			{ CD_VALUES : 14 , CD_DESCRIPTION : '경북' },
			{ CD_VALUES : 15 , CD_DESCRIPTION : '경남' },
			{ CD_VALUES : 16 , CD_DESCRIPTION : '제주' },
			
		]
	}
	
	$s.evt = {
		cancel : function(){
			location.href='./info.jsp';
		},
		submit : function(){
			alert('Hello')
		}
	}
	
	_fn.init();
}