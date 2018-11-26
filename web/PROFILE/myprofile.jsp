
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My profile</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" type="text/css" href="css/fontawesome-all.min.css">
    </head>
    <body class="app sidebar-mini rtl">
        <!-- Navbar-->
        <header class="app-header"><a class="app-header__logo" href="<c:url value="/Home" />"><img height="50" width="200" src="congiv.png"/> </a>
            <a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"><i class="fas fa-bars "></i></a>
            <ul class="app-nav">

                <!-- User Menu-->
                <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
                    <ul class="dropdown-menu settings-menu dropdown-menu-right">
                        <li><a class="dropdown-item" href="<c:url value="/myprofile"/>"><i class="fa fa-cog fa-lg"></i> Settings</a></li>
         
                        <li><a class="dropdown-item" href="<c:url value="/Logout"/>"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"> <i class="fas fa-user-circle fa-3x"></i>
                <div>
                    <p class="app-sidebar__user-name"> &ensp;&ensp;<b><c:url value=" ${username}" /></b> </p>

                </div>
            </div>
            <ul class="app-menu">
                <li><a class="app-menu__item active" href="<c:url value="/Home" />"><i class="app-menu__icon fas fa-eye"></i><span class="app-menu__label">Overview</span></a></li>
                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fas fa-sitemap"></i><span class="app-menu__label">VLANs Management</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a class="treeview-item" href="<c:url value="/listVlans" />"><i class="icon fas fa-list-ul"></i> List of Vlans</a></li>
                        <li><a class="treeview-item" href="<c:url value="/newVlan" />"><i class="icon fas fa-puzzle-piece"></i> Create New</a></li>
                    </ul>
                </li>
                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fas fa-hdd"></i><span class="app-menu__label">Equipment</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a class="treeview-item" href="<c:url value="/Equipment" />"><i class="fas fa-clipboard-list"></i> &ensp; Equipment List</a></li>
                        <li><a class="treeview-item" href="<c:url value="/addEquipment" />"> <i class="fas fa-plus"> </i>&ensp; Add new equipment</a></li>
                    </ul>
                </li>

                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Documentation</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a class="treeview-item" href="<c:url value="/documentation#Openstack" />"><i class="fas fa-cloud"></i> &ensp; Openstack</a></li>
                        <li><a class="treeview-item" href="<c:url value="/documentation#Dashboard" />"> <i class="fas fa-tachometer-alt"> </i>&ensp; Dashboard Development</a></li>
                        <li><a class="treeview-item" href="<c:url value="/documentation#Virtual_Machine" />"> <i class="fas fa-cube"> </i>&ensp; Virtual machine logic</a></li>
                        <li><a class="treeview-item" href="<c:url value="/documentation#Virtual_Networking" />"> <i class="fas fa-globe"> </i>&ensp; Networking</a></li>
                    </ul>
                </li>
              <li><a class="app-menu__item" href="<c:url value="/myprofile" />"><i class="app-menu__icon fas fa-user"></i><span class="app-menu__label">My Profile</span></a></li>  
            
              <li><a class="app-menu__item" href="<c:url value="/documentation#Contact" />"><i class="app-menu__icon fas fa-phone-volume"></i><span class="app-menu__label">Contact</span></a></li>  
            </ul>
        </aside>
 
    <main class="app-content">
      <div class="row user">
        <div class="col-md-12">
          <div class="profile">
            <div class="info">
                <h4><c:url value="${username}"/></h4>
              
            </div>
            <div class="cover-image"></div>
            
            
          </div>
                
                <div class="clearix"><br><br></div>
                <div class="col-md-12">
          <div class="tile">
            <h3 class="tile-title">Update credentials</h3>
 
                        <c:choose>
    <c:when test="${fn:contains(Error, 'java')}">
       <c:url value="" />
    </c:when>
    <c:otherwise>
        <c:url value="${Error}" />
    </c:otherwise>
</c:choose>
            <div class="tile-body">
                <form class="row" method="post" action="<c:url value="/myprofile"/>">

                  <br>
                  <div class="form-group col-md-3">
                  <label class="control-label">old password</label>
                  <input class="form-control" name="oldPass" type="password" placeholder="********" required>
                </div>
                <div class="form-group col-md-3">
                  <label class="control-label">new password</label>
                  <input class="form-control" name="newPass" type="password" placeholder="************" required>
                </div>
                  
                <div class="form-group col-md-4 align-self-end">
                  <button  class="btn btn-primary" type="submit"><i class="fa fa-fw fa-lg fa-check-circle"></i>Update</button>
                </div>
              </form>
            </div>
          </div>
        </div>
        </div>

        
      </div>
    </main>
    </body>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/plugins/pace.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
   
</html>
