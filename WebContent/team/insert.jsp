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
<link rel="stylesheet" type="text/css" href="/Whislte/assets/css/team/insert.css">
<script type='text/javascript' src="/Whislte/assets/scripts/controllers/team/insert.ctrl.js"></script>
</head>
<body ng-controller='teamInsertCtrl'>
    <jsp:include page="../common/Menu.jsp"/>
    <section class='body'>
        
        <md-content>
            <md-toolbar class="md-primary">
                <div class="md-toolbar-tools">
                  <h2 class="md-flex">Create Team</h2>
                </div>
            </md-toolbar>
            <md-content flex layout-padding >
                <md-input-container class="md-block">
                    <label>TeamName</label>
                    <input ng-model="mod.teamName">
                </md-input-container>
                <md-input-container class="md-block" flex-gt-sm>
                <label>Team City</label>
                <md-select ng-model="mod.teamCity">
                  <md-option ng-repeat="item in mod.cities" value="{{item.CD_VALUES}}">
                    {{item.CD_DESCRIPTION}}
                  </md-option>
                </md-select>
              </md-input-container>
            </md-content>
            
            <section layout="row" layout-sm="column" layout-align="center center" layout-wrap>
                <md-button class="md-raised  btn" ng-click='evt.cancel()'>Cancel</md-button>
                <md-button class="md-raised md-primary btn" ng-click='evt.submit()'>Submit</md-button>
            </section>
        </md-content>
        
        
    </section>
</body>
</html>
