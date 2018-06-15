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
<link rel="stylesheet" type="text/css" href="/Whislte/assets/css/team/info.css">
<script type='text/javascript' src="/Whislte/assets/scripts/controllers/team/team.info.ctrl.js"></script>

</head>
<body ng-controller='teamInfoCtrl'>
    <jsp:include page="../common/Menu.jsp"/>
    <section class='body'>
        <md-content>
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Team Info</h2>
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
        <md-content>
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Starter Info</h2>
                  
                </div>
            </md-toolbar>
            <md-content flex layout-padding >
                <table class='md-api-table'>
                    <tr>
                        <th>NUM</th>
                        <th>NAME</th>
                        <th>POS</th>
                        <th>MOVE</th>
                    </tr>
                    <tr ng-repeat='item in mod.player.starter'>
                        <td ng-bind='item.plyrBackNumber'></td>
                        <td ng-bind='item.plyrName'></td>
                        <td ng-bind='item.plyrPosition | position'></td>
                        <td >
                            <md-button ng-click='evt.plyr.movToBench(item)' class='md-icon-button' aria-label="change">
                                <md-icon md-svg-icon="/Whislte/assets/images/icons/angle-arrow-down.svg"></md-icon> 
                            </md-button>
                        </td>
                    </tr>
                </table>
            </md-content>
        </md-content>
        <md-content>
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Member info</h2>
                  <md-button ng-click='evt.plyr.insert($event)' class='md-icon-button btn-add' aria-label="add">
                        <md-icon md-svg-icon="/Whislte/assets/images/icons/add.svg"></md-icon> 
                    </md-button>
                </div>
            </md-toolbar>
            <md-content flex layout-padding>
                <table class='md-api-table'>
                    <tr>
                        <th>NUM</th>
                        <th>NAME</th>
                        <th>POS</th>
                        <th>MOVE</th>
                    </tr>
                    <tr ng-repeat='item in mod.player.bench'>
                        <td ng-bind='item.plyrBackNumber '></td>
                        <td ng-bind='item.plyrName'></td>
                        <td ng-bind='item.plyrPosition | position'></td>
                         <td >
                            <md-button ng-click='evt.plyr.movToStart(item)' class='md-icon-button' aria-label="change">
                                <md-icon md-svg-icon="/Whislte/assets/images/icons/up-arrow.svg"></md-icon> 
                            </md-button>
                        </td>
                    </tr>
                </table>
            </md-content>
        </md-content>
        
    </section>
</body>
</html>
