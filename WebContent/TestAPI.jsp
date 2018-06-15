<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='whistleApp'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/head.jsp" />

<script>
	window['_ANG_MAIN_MODULE'].controller('testCtrl', [ '$scope', '$rootScope',
			'userServ', testCtrl ]);

	function testCtrl($s, $rs, $userServ) {
		var _fn = {
			init : function() {
				console.log('Hello!');
			}
		}

		$s.mod = {
			user : [ {
				name : 'login',
				description : '로그인'
			}, {
				name : 'signup',
				description : '회원가입'
			}, {
				name : 'logout',
				description : '로그아웃'
			} ],
			team : [ {
				name : 'create',
				description : '팀 생성'
			}, {
				name : 'modify',
				description : '팀 수정'
			}, {
				name : 'setManager',
				description : '매니저 선택'
			}, {
				name : 'delete',
				description : '팀 삭제'
			}, {
				name : 'select',
				description : '내 팀 조회'
			}, ],
			player : [ {
				name : 'insert',
				description : '선수 생성'
			}, {
				name : 'update',
				description : '선수 변경'
			}, {
				name : 'delete',
				description : '선수 삭제'
			}, {
				name : 'insertAtTeam',
				description : '선수를 팀에 할당'
			}, {
				name : 'removeFromTeam',
				description : '선수를 팀에서 없앰'
			}, {
				name : 'get',
				description : '내 팀의 선수들을 가져옴 페이징 지원되어야'
			} ]
		};

		$s.fn = {
			eval : function(fn, cate) {
				if (cate === "user") {
					switch (fn) {
					case 'login':
						_fn.user.login();
						break;
					case 'signup':
						_fn.user.signup();
						break;
					case 'logout':
						_fn.user.logout();
						break;

					}
				}

			}
		}

		var _fn = {
			user : {
				login : function() {
				    
				},
				signup : function(){
					
				},
				logout : function(){
					
				}
			},
			team : {
				create: function(){
					
				},
				modify : function(){
					
				},
				setManager : function(){
					
				},
				delete : function(){
					
				},
				select : function(){
					
				}
			}
		}

		console.log('hello');
	}
</script>

<style>
</style>

</head>
<body ng-controller='testCtrl'>
    <div class='container'>
        <table class='table table-hover text-center'>
            <tr class='thead-dark'>
                <th>API NAME</th>
                <th>API DESCRIPTION</th>
                <th>TEST</th>
            </tr>
            <tr>
                <td colspan='3' class='table-primary'>USER</td>
            </tr>
            <tr ng-repeat='item in mod.user'>
                <td ng-bind='item.name'></td>
                <td ng-bind='item.description'></td>
                <td>
                    <button ng-click='fn.eval(item.name, "user")'
                        type='button'>실행</button>
                </td>
            </tr>
            <tr>
                <td colspan='3' class='table-primary'>TEAM</td>
            </tr>
            <tr ng-repeat='item in mod.team'>
                <td ng-bind='item.name'></td>
                <td ng-bind='item.description'></td>
                <td>
                    <button ng-click='fn.eval(item.name , "team")'
                        type='button'>실행</button>
                </td>
            </tr>
            <tr>
                <td colspan='3' class='table-primary'>PLAYER</td>
            </tr>
            <tr ng-repeat='item in mod.player'>
                <td ng-bind='item.name'></td>
                <td ng-bind='item.description'></td>
                <td>
                    <button ng-click='fn.eval(item.name , "player")'
                        type='button'>실행</button>
                </td>
            </tr>

        </table>
    </div>
</body>
</html>