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
            <md-content flex layout-padding class='team-info empty' ng-if='mod.team.info.teamSeq == -1'>
                <div class='cover'>
                    <h1 class='md-headline center warn'>팀이 아직 없어요</h1>
                    <md-button class="md-raised md-primary btn" ng-click='evt.team.create()'>Create Team</md-button>
                </div>
            </md-content>
            <md-content flex layout-padding class='team-info' ng-if='mod.team.info.teamSeq > 0'>
               팀명 : <h1 class='md-headline' ng-bind='mod.team.info.teamName'></h1>
                
            </md-content>
            
        </md-content>
        
    </section>
</body>
</html>
