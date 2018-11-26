<%-- 
    Document   : Home
    Created on : Mar 1, 2018, 2:59:46 PM
    Author     : yassine Fadhlaoui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
                    <h1><i class="fa fa-dashboard"></i> Openstack Management Dashboard</h1>
                    <p>Instances management</p>
                </div>
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
                    <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-3">
                    <div class="widget-small primary coloured-icon"><i class="icon fas fa-desktop fa-3x"></i>
                        <div class="info">
                            <h4>Active instances</h4>
                            <p><b><c:out value="${ Nservers }"/></b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="widget-small info coloured-icon"><i class="icon fas fa-cloud fa-3x"></i>
                        <div class="info">
                            <h4>VLANs</h4>
                            <p><b><c:out value="${ Nvlans }"/></b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="widget-small warning coloured-icon"><i class="icon fas fa-microchip fa-3x"></i>
                        <div class="info">
                            <h4>CPU Load</h4>
                            <p><b><c:out value="${ cpuLoad }"/></b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="widget-small danger coloured-icon"><i class="icon fas fa-chart-pie fa-3x"></i>
                        <div class="info">
                            <h4>RAM</h4>
                            <p><b><c:out value="${ UsedMem }"/> %</b></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="tile">
                        <h3 class="tile-title">CPU chart</h3>
                        <div class="embed-responsive embed-responsive-16by9">
                            <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="tile">
                        <h3 class="tile-title">RAM chart</h3>
                        <div class="embed-responsive embed-responsive-16by9">
                            <p><center>Total RAM: <c:out value="${ TotalMem }"/> GB</center></p>
                            <canvas class="embed-responsive-item" id="pieChartDemo"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="js/jquery-3.2.1.min.js" ></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/plugins/pace.min.js"></script>
        <script type="text/javascript" src="js/plugins/chart.js"></script>
        <script type="text/javascript">
            
            var ctxp = $("#pieChartDemo").get(0).getContext("2d");
            var pieChart = new Chart(ctxp,
                    {
                        "type": "doughnut",
                        "data":
                                {"labels":
                                            [
                                                "Used RAM %",
                                                "Available RAM %",
                                                "Free RAM %"],
                                    "datasets": [
                                        {"label": "RAM chart",
                                            "data": [<c:out value="${ UsedMem }"/>,<c:out value="${ AvailableMem }"/>,<c:out value="${ FreeMem }"/>],
                                            "backgroundColor":
                                                    ["rgb(255, 77, 77)", "rgb(6, 159, 187)", "rgb(0, 156, 138)"]
                                        }
                                    ]
                                },
                        "options": ['cutoutPercentage']
                    }
            );
            //END OF RAM PIE CHART

//REAL TIME LOAD AVERAGE
var ctxl = $("#lineChartDemo").get(0).getContext("2d");
  var data = {
    labels: [],
    datasets: [{
      label: "CPU Average Load",
      fillColor: "rgba(220,220,220,0.2)",
      strokeColor: "rgba(51, 153, 255,1)",
      pointColor: "rgba(220,220,220,1)",
      pointStrokeColor: "#fff",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "rgba(220,220,220,1)",
      data: []
    }]
  };
  var options = {
  animation: false,
  scaleOverride: true,
  scaleSteps: 10,
  scaleStepWidth: 10,
  scaleStartValue: 0
  };
  var myLineChart = new Chart(ctxl, {
    type: 'line',
    data: data,
    options: options
});

  setInterval(function() {
    setData(data.datasets[0].data);
    setLabels(data.labels);
    var myLineChart = new Chart(ctxl, {
    type: 'line',
    data: data,
    options: options
});
  }, 5000);

  function setLabels(labels) {
    var day = new Date();
    var hh= day.getHours();
    var mm= day.getMinutes();
    var ss= day.getSeconds();
    if (mm.toString().length<2) mm="0"+mm;
    if (ss.toString().length<2) ss="0"+ss;
    var label=hh+":"+mm+":"+ss;
    labels.push(label);
    if(labels.length > 10)
    labels.shift();
  }

  function setData(data) {
    $.ajax(
            {
                url: "/DashboardManagement/cpuAverage",
                async: true,  
                type: "GET", 
                dataType: "text", 
                success: function(cpuAverage)
                {
                   data.push(cpuAverage);
                   if(data.length > 10)
                   data.shift(); 
                }

            }
        );
    
  }
        </script>

    </body>
</html>
