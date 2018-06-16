<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//System.out.println(session.getAttribute("userSeq"));
if(session.getAttribute("userSeq") != null){
    response.sendRedirect("../Main.jsp");
}
%>
<html data-ng-app="whistleApp">
<head>
	<jsp:include page="../common/loginHead.jsp" />
	<link rel="stylesheet" type="text/css" href="../assets/css/user/login.css">
	<script type='text/javascript' src="../assets/scripts/controllers/login.ctrl.js"></script>
</head>
<body ng-controller="loginCtrl">
	<form class="form-signin">
      <div class="text-center mb-4">
        <img class="mb-4" src="/Whislte/assets/images/logo.png" alt="" width="180" height="180">
        
            
      </div>

      <div class="form-label-group">
        <input ng-model='mod.user.mail' type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputEmail">Email address</label>
      </div>

      <div class="form-label-group">
        <input ng-model='mod.user.password' type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <label for="inputPassword">Password</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <div class="checkbox mb-3">
        <label>
          <a href="/Whislte/users/signup.jsp">아직 계정이 없으신가요 ?</a>
        </label>
      </div>
      
      
        <div class="alert alert-danger" role="alert" ng-if='mod.err.cd < 0' >
            {{ mod.err.msg }}
            <button ng-click='fn.err.clear()' type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>

      <button ng-click='fn.evt.login()' class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
      <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
    </form>
</body>
<footer>
	<jsp:include page="../common/footer.jsp"/>
</footer>
</html>