<%-- 
    Document   : VLANList
    Created on : Mar 24, 2018, 3:34:14 PM
    Author     : yassine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equipment</title>
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
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i> Equipment List</h1>
          <p>Table to display equipment data effectively</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Equipment</li>
          <li class="breadcrumb-item active"><a href="#">Equipment list</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                  <tr>
                      
                    <th><i class="fas fa-hashtag"></i> ID</th>
                    <th><i class="fas fa-sort-numeric-up"></i> source CO Number</th>
                    <th> <i class="fas fa-industry"></i> source device Port</th>
                    <th><center><i class="fas fa-edit"></i> VLAN</center></th>
                    <th><center><i class="fas fa-sort-numeric-down"></i> Target CO Number</center></th>
                    <th><center><i class="fas fa-plug"></i> Target device Port</center></th>
                     <th><center><i class="fas fa-tachometer-alt"></i> status </center></th> 
                     <th><center><i class="fas fa-comments"></i> comment</center></th> 
                    <th><center><i class="fas fa-plug"></i> Connect</center></th> 
<th><center><i class="fas fa-trash-alt"></i> Delete</center></th> 
</tr>
                </thead>
                <tbody>
                 <c:forEach items="${EquipmentList}" var="equipment">
                    <tr>
                    <td><c:out value="${equipment.equipmentID}"/></td>
                    <td><center><c:out value="${equipment.sourceCONumber}"/></center></td>
                     <td><center><c:out value="${equipment.sourcedevicePort}"/></center></td>
                    <td><c:out value="${equipment.vlanid}"/></td>
                    <td><c:out value="${equipment.destinationCONumber}"/></td>
                    <td><c:out value="${equipment.destinationPort}"/></td>
                    <td><c:out value="${equipment.status}"/></td>
                    <td><c:out value="${equipment.comment}"/></td>
                    <c:choose>
    <c:when test="${equipment.vlanid=='1'}">
        <td>Cannot connect to this device</td>
    </c:when>    
    <c:otherwise>
        <c:choose>
            <c:when test="${equipment.vlanid=='trunk'}">
                <td>Cannot connect to this device</td>
            </c:when>    
            <c:otherwise>
                 <td><center><button class="btn btn-outline-secondary" type="button"> <a href="<c:url value="/newVlan?vlan=vlan-${equipment.vlanid}-${equipment.comment}&vlanid=${equipment.vlanid}" />">Connect</a></button></center></td>
            </c:otherwise>
       </c:choose>
 </c:otherwise>
</c:choose>
               <td><center><button class="btn btn-outline-secondary" type="button"> <a href="<c:url value="/Equipment?id=${equipment.equipmentID}" />">Delete</a></button></center></td>
            
                       </tr> 
                   </c:forEach>
               
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
                    
                    
                    
   <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/plugins/pace.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">$('#sampleTable').DataTable();</script>
    </body>
</html>
