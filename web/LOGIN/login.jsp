<%-- 
    Document   : login
    Created on : Mar 12, 2018, 12:38:16 PM
    Author     : yassine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
        <form class="login-form" action="<c:url value="/Login" />" method="post">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>SIGN IN</h3>
          <div class="form-group">
            <label class="control-label">USERNAME</label>
            <input class="form-control" type="text" name="username" placeholder="Congiv" autofocus required>
             <c:choose>
                            <c:when test="${fn:contains(error, 'sessionid')}">
                                <c:url value="" />
                            </c:when>
                            <c:otherwise>
                               <c:url value="${error}" />
                            </c:otherwise>
                        </c:choose>
            
          </div>
          <div class="form-group">
            <label class="control-label">PASSWORD</label>
            <input class="form-control" type="password" name="password" placeholder="***********" required>
          </div>
          <div class="form-group">
            <div class="utility">
                
            
            </div>
              <br>
          <div class="form-group btn-container" >
              <center>
              <table>
                  <tr>
                      <td><button type="submit"  class="btn btn-primary btn-block" > SIGN IN</button>
         </td>
         <td style="width: 30%"> <center>or </center></td>
                      <td><button id="signin" type="button" onclick="bringMeToLife()" class="btn btn-primary btn-block" > SIGN UP</button>
          </td>
                  </tr>
              </table>
              </center>
          </div>
        </form>
        
      </div>
    </section>
          <script>
              function bringMeToLife(){
                  document.location.href="/DashboardManagement/SignUp";
              }
              
          </script>
    </body>
</html>
