<%-- 
    Document   : error
    Created on : Mar 21, 2018, 11:21:20 AM
    Author     : yassine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>403 Forbidden</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
          <div class="col-md-12">
        <main class="app-content">
           
      <div class="page-error tile">
        <h1> Error 403: Forbidden</h1>
        <p><c:url value="${error}" /></p>
      </div>
        
    </main>
               </div>
    </body>
</html>

