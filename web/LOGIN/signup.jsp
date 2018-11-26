<%-- 
    Document   : login
    Created on : Mar 12, 2018, 12:38:16 PM
    Author     : yassine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
            <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
        
        <title>Login | Congiv</title>
    </head>
    <body>
        <script src="js/plugins/pace.min.js"></script>
            <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
            <section class="material-half-bg">
      <div class="cover" style="background-color: #5B5B5B"></div>
    </section>
        <section class="login-content" >
      <div class="logo" >
        <img src="LOGIN/congiv.png" />
      </div>
      <div class="login-box">
        <form class="login-form" action="<c:url value="/SignUp" />" method="post">
          <h3 class="login-head"><i class="fas fa-user-plus"></i>SIGN UP</h3>
          <div class="form-group">
            <label class="control-label">USERNAME</label>
            <input class="form-control" type="text" name="username" placeholder="Congiv" autofocus required>
            <c:url value="${error}" />
          </div>
         <div class="form-group">
            <label class="control-label">EMAIL</label>
            <input class="form-control" type="text" name="email" placeholder="congiv.employee@congiv.de" autofocus required>
          </div>

          <div class="form-group">
            <label class="control-label">PASSWORD</label>
            <input class="form-control" type="password" name="password" placeholder="***********" required>
          </div>
          <div class="form-group">
         
       
          <div class="form-group btn-container">
            <button type="submit"  class="btn btn-primary btn-block"><i class="fa fa-sign-in fa-lg fa-fw"></i>SIGN UP</button>
          </div>
        </form>
        
      </div>
    </section>

    </body>
</html>
