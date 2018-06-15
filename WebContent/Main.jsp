<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//System.out.println(session.getAttribute("userSeq"));
if(session.getAttribute("userSeq") == null){
    response.sendRedirect("./users/login.jsp");
}
%>
<html ng-app='whistleApp'>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Whistle</title>

<jsp:include page="/common/head.jsp" />
<link rel="stylesheet" type="text/css" href="/Whislte/assets/css/main.css">
<script type='text/javascript' src="/Whislte/assets/scripts/controllers/main.ctrl.js"></script>

</head>
<body>
    <jsp:include page="/common/Menu.jsp"/>
    
    MAIN!
</body>
</html>