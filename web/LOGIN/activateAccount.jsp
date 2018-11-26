<%-- 
    Document   : newuser
    Created on : Mar 21, 2018, 12:01:54 PM
    Author     : yassine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>confirmation</title>
                <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
          <div class="col-md-12">
           
      <div class="bs-component">
              <div class="alert alert-dismissible alert-warning">
                <button class="close" type="button" data-dismiss="alert">×</button>
                <h4>Account not activated yet!</h4>
                <p><b><c:url value="${username}" /></b>. One last step, We need you to confirm your account. We sent you an activation mail to your address <strong><c:url value="${email}" /></strong>. Please activate your account first.</p>
              </div>
            </div>

</div>
        <script src="js/jquery-3.2.1.min.js" ></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/plugins/pace.min.js"></script>
        <script type="text/javascript" src="js/plugins/chart.js"></script>
    </body>
</html>


