<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
if(session.getAttribute("userSeq") == null){
    response.sendRedirect("../users/login.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="whistleApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Whistle</title>
<jsp:include page="../common/head.jsp"/>
<link rel="stylesheet" type="text/css" href="/Whislte/assets/css/game/main.css">
<script type='text/javascript' src="/Whislte/assets/scripts/controllers/game/game.main.ctrl.js"></script>

</head>
<body ng-controller='gameMainCtrl'>
    <jsp:include page="../common/Menu.jsp"/>
    <section class='body'>
        <md-content>
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Game Info</h2>
                </div>
            </md-toolbar>
            
            <!-- Team이 없는 경우  -->
            <md-content flex layout-padding class='team-info empty' >
                <div class='cover'>
                    <h1 class='md-headline center warn' ng-bind='mod.game.info.gameState === "1" ? "진행중" : "종료"'></h1>
                    <md-button class="md-raised md-primary btn" ng-click='evt.game.view()'>Join Game</md-button>
                    <p style="text-align: right; font-size: 18px; font-weight : 800; color : white;">
                        HOME {{ mod.game.score.home }} : AWAY {{ mod.game.score.away }} {{mod.game.timeTxt}}
                    </p>
                </div>
            </md-content>
            
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Game History</h2>
                </div>
            </md-toolbar>
            
            <md-content flex layout-padding>
                <table class='md-api-table'>
                    <tr>
                        <th>Date</th>
                        <th>Home</th>
                        <th>Away</th>
                        <th>Detail</th>
                    </tr>
                    <tr ng-repeat='item in mod.game.history'>
                        <td ng-bind='item.date | frmDate'></td>
                        <td ng-bind='item.homeScore'></td>
                        <td ng-bind='item.awayScore '></td>
                         <td >
                            <md-button class='md-primary' ng-click='evt.plyr.movToStart(item)'  aria-label="change">
                                Detail
                            </md-button>
                        </td>
                    </tr>
                </table>
            </md-content>
        </md-content>
            
        </md-content>
        
    </section>
</body>
</html>
