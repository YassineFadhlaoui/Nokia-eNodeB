<%-- 
    Document   : Home
    Created on : Mar 1, 2018, 2:59:46 PM
    Author     : yassine Fadhlaoui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Documentation</title>
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
                    <h1><i class="fas fa-magic"></i> Create new VLAN</h1>
                    <p>Create new VLAN to connect and manage new BTS</p>
                </div>
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
                    <li class="breadcrumb-item">VLANs Management</li>
                    <li class="breadcrumb-item"><a href="#">Create New</a></li>
                </ul>
            </div>


            <div class="row ">
                <div class="col-md-2 "></div>
                <div class="col-md-8 ">
                    <div class="tile">
                        <h3 class="tile-title">New VLAN creation</h3>
                        <c:choose>
                            <c:when test="${fn:contains(error, 'java')}">
                                <c:url value="" />
                            </c:when>
                            <c:otherwise>
                                <c:url value="${error}" />
                            </c:otherwise>
                        </c:choose>
                        <div class="tile-body">
                            <form action="<c:url value="/newVlan" />" method="post">
                                <div class="form-group">
                                    <label class="control-label">VLAN name: </label>
                                    <div class="input-group">
                                        <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-pencil-alt"></i></span></div>
                                        <input name="name" class="form-control" id="name" type="text" placeholder="NOKIA-VALN-100"  value="<c:url value="${VlanName}" />" required>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label">Segmentation ID: </label>
                                    <div class="input-group">
                                        <div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-sort-numeric-up"></i></span></div>
                                        <input name="segID" class="form-control" id="segID" type="text" placeholder="100,102,110-120" value="<c:url value="${VlanID}" />"required>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="exampleSelect1">Equipement type:</label>
                                    <select name="equipement" class="form-control" id="type">
                                        <option>NOKIA</option>
                                        <option>HUAWEI</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label> Enable Multithreading: <i id="SpanInfo" class="fas fa-question-circle " onmouseover="" style="cursor: pointer;"></i></label>
                                    <div class="bs-component">
                                        <div class="infospan"></div>
                                    </div>
                                    <div class="toggle-flip">
                                        <label>
                                            <input type="checkbox" name="multithreading" id="multithreading"><span class="flip-indecator" data-toggle-on="ON" data-toggle-off="OFF"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="tile-footer">
                                    <button class="btn btn-outline-primary" id="demoSwal" type="button" >Validate</button>
                                    <button class="btn btn-outline-primary" id="validateButton" type="submit" disabled>Create VLAN</button>
                                    <div id="LoaderItem"></div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 "></div>
                </div>
                <script src="js/jquery-3.2.1.min.js" ></script>
                <script type="text/javascript" src="js/plugins/bootstrap-notify.min.js"></script>
                <script type="text/javascript" src="js/plugins/sweetalert.min.js"></script>
                <script>
                    var segID = document.getElementById("segID").value
                    $(document).ready(function () {
                        var $form = $('form');
                        $form.submit(function () {
                            $("#validateButton").prop('disabled', true);
                            var Loader = '<div id="Loading" class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div>'
                            $("#LoaderItem").append(Loader)

                            $.post($(this).attr('action'), $(this).serialize(), function (response) {

                                $("#Loading").remove()
                                swal({
                                    title: "Request submitted",
                                    text: "Do you want to be redirected to vlan lists ?",
                                    type: "success",
                                    showCancelButton: true,
                                    confirmButtonText: "Stay",
                                    cancelButtonText: "GO",
                                    closeOnConfirm: false,
                                    closeOnCancel: false
                                }, function (isConfirm) {
                                    if (isConfirm) {

                                        swal.close();

                                    } else {
                                        document.location.href = "/DashboardManagement/listVlans";
                                    }
                                });




                            }
                            ).fail(function () {
                                $("#Loading").remove()
                                alert("VLAN with Segmentaion ID: "+segID+" is already in use,\n please destroy it first");
                            })
                                    ;
                            return false;
                        });
                    });
                </script>
                <script>
                    $('#demoSwal').click(function () {
                        var name = document.getElementById("name").value;
                        var segID = document.getElementById("segID").value;
                        var equipement = document.getElementById("type");
                        var type = equipement.options[equipement.selectedIndex].value;
                        var multithreading = document.getElementById("multithreading").checked;
                        var verify = 0;
                        var text = "";
                        var swalType = "warning";
                        var title = "VLAN caracteritics";
                        if (name.length === 0) {
                            text = "Please provide a VLAN name";
                            title = "VLAN name is required";
                            swalType = "error";
                            verify = 1;
                        }
                        if (name.length > 0 && (segID < 100 || segID > 4096)) {
                            text = "Please provide a vlan id in the range [100,4096] ";
                            title = "VLAN ID is required";
                            swalType = "error";
                            verify = 1;
                        }
                        if (verify === 0) {
                            text = "VLAN name: " + name + "\n" +
                                    "Segmentation ID: " + segID + "\n" +
                                    "BTS Manufacturier: " + type + "\n" +
                                    "Multithreading: " + multithreading
                        }
                        swal({
                            title: title,
                            text: text,
                            type: swalType,
                            showCancelButton: true,
                            confirmButtonText: "OK",
                            cancelButtonText: "Cancel",
                            closeOnConfirm: false,
                            closeOnCancel: false
                        }, function (isConfirm) {
                            if (isConfirm) {
                                if (verify === 1) {
                                    swal.close();
                                } else {
                                    swal("VLAN Validated", "The data that you put is valid. You can now create the VLAN by hitting create VLAN button", "success");
                                    $("#validateButton").removeAttr('disabled');
                                }

                            } else {
                                swal("Cancelled", "Validatation cancelled!", "error");
                            }
                        });
                    });
                </script>

                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/main.js"></script>
                <script src="js/plugins/pace.min.js"></script>

                <script type="text/javascript" src="js/plugins/chart.js"></script>

                </body>  


                </html>
