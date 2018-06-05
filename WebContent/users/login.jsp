<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="whistleApp">
<head>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" type="text/css" href="../assets/css/user/login.css">
	<script type='text/javascript' src="../assets/scripts/controllers/login.ctrl.js"></script>
</head>
<body ng-controller="loginCtrl">
	<form class="form-signin">
      <div class="text-center mb-4">
        <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Whistle</h1>
        <p>
			
    		</p>
      </div>

      <div class="form-label-group">
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputEmail">Email address</label>
      </div>

      <div class="form-label-group">
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
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
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
    </form>
</body>
<footer>
	<jsp:include page="../common/footer.jsp"/>
</footer>
</html>