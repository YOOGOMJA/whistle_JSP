<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="whistleApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Whistle - Signup </title>
    <jsp:include page="../common/loginHead.jsp" />
    <link rel="stylesheet" type="text/css" href="../assets/css/user/signup.css">
    <script type='text/javascript' src="../assets/scripts/controllers/signup.ctrl.js"></script>
    
</head>
<body ng-controller='signupCtrl'>
    <form class="form-signin">
      <div class="text-center mb-4">
        <img class="mb-4" src="/Whislte/assets/images/logo.png" alt="" width="180" height="180">
        
      </div>
      
      <div class="form-label-group">
        <input ng-model='mod.email' type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputEmail">Email</label>
      </div>
      
      <div class="form-label-group">
        <input ng-model='mod.name' type="text" id="inputName" class="form-control" placeholder="Name" required="" autofocus="">
        <label for="inputName">Name</label>
      </div>
      <div class="form-label-group">
        <input ng-model='mod.password' type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
        <label for="inputPassword">Password</label>
      </div>
      <div class="form-label-group">
        <input ng-model='mod.password2' type="password" id="inputPassword2" class="form-control" placeholder="Password" required="">
        <label for="inputPassword2">Password Validation</label>
      </div>
      
    <div class="alert alert-danger" role="alert" ng-if='mod.err.cd < 0' >
        {{ mod.err.msg }}
        <button ng-click='fn.evt.err.clear()' type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    
    <div class="checkbox mb-3">
        <label>
          <a href="/Whislte/users/login.jsp">이미 계정이 있으신가요 ?</a>
        </label>
      </div>
      

      <button ng-click='fn.evt.submit()' class="btn btn-lg btn-primary btn-block" type="button">Sign up</button>
      <p class="mt-5 mb-3 text-muted text-center">© 2017-2018</p>
    </form>
</body>
</html>