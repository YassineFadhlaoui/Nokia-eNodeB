<%-- 
    Document   : doc
    Created on : Mar 29, 2018, 1:09:43 PM
    Author     : yassine
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <li><a class="app-menu__item" href="<c:url value="/Home" />"><i class="app-menu__icon fas fa-eye"></i><span class="app-menu__label">Overview</span></a></li>
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

                <li class="treeview active"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Documentation</span><i class="treeview-indicator fa fa-angle-right"></i></a>
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
                    <h1><i class="fa fa-dashboard"></i> Project Documentation</h1>
                    <p></p>
                </div>
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
                    <li class="breadcrumb-item"><a href="#">Documentation</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="bs-component">
                    <div class="jumbotron">
                        <h1  class="display-3"><a href="#Openstack">Openstack</a></h1>
                        <p><center><img src="DOCUMENTATION/images/OpenStack.png" height="100px" width="200px" /></center><br>
                            
                         This project uses OpenStack as backend. OpenStack is a cloud operating system that controls large pools of compute and networking resources. 
                         You can find consistent Documentation below: 
                        <ul>
                            <li><a href="https://www.openstack.org/software/">What is OpenStack?</a></li>
                            <li><a href="https://docs.openstack.org/security-guide/introduction/introduction-to-openstack.html">Introduction to OpenStack</a></li>
                            <li><a href="https://docs.openstack.org/mitaka/install-guide-ubuntu/overview.html">Openstack Overview</a></li>
                        </ul>
                         <b>E-Books</b>
                         <ul>
                             <li><a href="DOCUMENTATION/pdf/OpenStackCookbookV2.pdf">OpenStack Cookbook V2 </a><i>(legally downloaded from www.packtpub.com)</i></li>
                             <li><a href="DOCUMENTATION/pdf/networking-guide_Neutron.pdf">networking guide Neutron</a></li>
                         </ul>
                            
                            
                            
                        </p>
                        
                    </div>
                </div>
                
             
                    <div class="jumbotron">
                        <h1  class="display-3"><a href="#Dashboard">Dashboard</a></h1>
                        <p><center><img src="DOCUMENTATION/images/dash.png" height="200px" width="400px" /></center><br>
                           The Dashboard is web based application that consumes OpenStack endpoints in order to create and manage networks and virtual machines. it is developed using the following programming languages:
                           <ul>
                               <li>JAVA 8.1</li>
                               <li>Javascript</li>
                               
                           </ul>
                           The server that is used to run this web application is <b> GlassFish v4.1 </b>
                           <br>
                           The database used is <b>mariaDB v10.0.34</b>
                           <br>
                           The Dashboard code consists of 8 packages:
                           <ul>
                               <li><b>Heat:</b> to retrieve, post and parse data from the Openstack heat endpoint. <a href="DOCUMENTATION/Heat/package-frame.html">read more ...</a> </li>
                               <li><b>Template:</b> to create a VLAN template and configure the networks and the instance. <a href="DOCUMENTATION/Template/package-frame.html">read more ...</a></li>
                               <li><b>entities:</b> java beans entities used to map mysql tables to java classes. <a href="DOCUMENTATION/entities/package-frame.html">read more ...</a></li>
                               <li><b>helpers:</b> package that contains helper classes (classes to send mail, AES Cipher, Configuration file loader ...). <a href="DOCUMENTATION/helpers/package-frame.html">read more ...</a>  </li>
                               <li><b>networks:</b> package that contains classes to interact, delete, add and retrieve OpenStack neutron networks. <a href="DOCUMENTATION/networks/package-frame.html">read more ...</a>  </li>
                               <li><b>servers:</b> this package includes java classes that interact with nova module to retrive information about instances. <a href="DOCUMENTATION/servers/package-frame.html">read more ...</a></li>
                               <li><b>servlets: </b> the most important package that  Prescribes how business objects interact with one another
and enforces the routes and the methods by which business objects are accessed and updated <a href="DOCUMENTATION/servlets/package-frame.html">read more ...</a></li>
                               <li><b>sessions:</b> java sessions beans package that contains <b>Stateless</b> ejb sessions ( because the EJB container does not need to keep track of their state across method calls. ). each session bean is responsible for a group of related functionality. For example, the User session bean is responsible of handling user creation, update and authentication. 
                               <a href="DOCUMENTATION/sessions/package-frame.html">read more ...</a></li>
                               
                           </ul>
                            
                            The figure below shows the logical architecture of the web application:
                            <center><img heigth="800px" width="800px" src="DOCUMENTATION/Slide1.jpg"/></center>
                        </p>
                        
                    </div>
                 <div class="jumbotron">
                        <h1  class="display-3"><a href="#" id="Virtual_Machine">Virtual Machine</a></h1>
                        <p>OpenStack virtual machines are called instances,
                            mostly because they are instances of an image that is created upon request and that is configured when launched. The main difference between OpenStack and traditional virtualization technology is the way state is stored. With traditional virtualization technology, the state of the virtual machine is persistent.
                          
                            <br>
                            In this project we used an <b>ephemeral instance </b> approach. In the ephemeral model, an instance is launched from an image in the Image service, the image is copied to the run area, and when the copy is completed, the instance starts running. The size and connectivity of the instance are defined at the time of launching the instance. When an instance is terminated, the original image remains intact, but the state of the terminated instance is not retained. This ephemeral model is useful for scaling out quickly and maintaining agility for users.
                            <br><i>oracle</i>
                            
                           Once the instance is created. the heat template engine configures it by doing the following:
                        <ul>
                            <li>Adding a new virtual network card interface</li>
                            <li>Create a second routing table to support multiple gateways</li>
                            <li>Tune the <i>MTU</i> to make ssh possible</li>
                            <li>
                                associate floating IP from the management network to the newly created interface and a floating ip from the VLAN x to the first interface.
                            </li>
                        </ul>
                        The bash script that is injected by the Heat engine to the newly created instance is the following:
                        <code>
                            
                            <br>#!/bin/bash<br>
                            <br># specify the default shell (bash)<br>
sudo route delete default gw 10.10.0.1 eth1<br>
<br>#delete the default route to the management network gateway<br>
sudo route delete default gw 10.0.0.1 eth0
<br>#delete the default route to the VLAN gateway<br>
sudo route add default gw 10.0.0.1 metric 1 eth0<br>
sudo route add default gw 10.10.0.1 metric 10 eth1<br>
sudo echo '1 rt2' >> /etc/iproute2/rt_tables
<br>#add a routing tabe to support multiple gateways usage (a first routing table for the management network)<br>
sudo echo '2 rt3' >> /etc/iproute2/rt_tables
<br>#add a routing tabe to support multiple gateways usage (a first routing table for the vlan network)<br>

SECOND_INTERFACE=$(ip a| grep inet | awk {'print $2'} | grep 10.10.0 | awk -F/ {'print $1'} )<br>
<br># extract the ip address assigned to the management NIC<br>
FIRST_INTERFACE=$(ip a| grep inet | awk {'print $2'} | grep 10.0.0 | awk -F/ {'print $1'} ) <br> 
<br># extract the ip address assigned to the vlan NIC<br>
sudo ip route add 10.10.0.0/16 dev eth1 src $SECOND_INTERFACE table rt2<br>
<br># add default route to routing table rt2<br>
sudo ip route add 10.0.0.0/8 dev eth0 src $FIRST_INTERFACE table rt3<br>
<br># add default route to routing table rt3<br>
sudo ip route add default via 10.10.0.1 dev eth1 table rt2<br>
<br># add default gateway on table rt2<br>
sudo ip route add default via 10.0.0.1 dev eth0 table rt3<br>
<br># add default route to default gateway on table rt3<br>
sudo ip rule add from $SECOND_INTERFACE/32 table rt2<br>
sudo ip rule add from $FIRST_INTERFACE/32 table rt3<br>
sudo ip rule add to $SECOND_INTERFACE/32 table rt2<br>
sudo ip rule add to $FIRST_INTERFACE/32 table rt3<br>
<br>adding rules to both routing tables
sudo ip link set mtu 1400 dev eth1<br>
<br># tune up the instance mtu<br>
sudo ip route add 192.168.255.0/24 via 10.0.0.1<br>
sudo ip route add 172.31.0.0/16 via 10.10.0.1<br>
<br># add routes to floating networks
                            
                            
                        </code>
                        <br>
                        The figure below shows how an instance is logically connected to the management network and the VLANi.
          <center><img heigth="800px" width="800px" src="DOCUMENTATION/Slide2.jpg"/></center>
          
                                           
                        </p>
                        
                    </div>
                
                
                
                   <div class="jumbotron">
                        <h1  class="display-3"><a href="#Virtual_Networking" id="Virtual_Networking">Networking</a></h1>
                        <p>
                            The OpenStack Networking service provides an API that allows users to set up and define network connectivity and addressing in the cloud. The project code-name for Networking services is neutron. OpenStack Networking handles the creation and management of a virtual networking infrastructure, including networks, switches, subnets, and routers for devices managed by the OpenStack Compute service (nova). Advanced services such as firewalls or virtual private networks (VPNs) can also be used.

OpenStack Networking consists of the neutron-server, a database for persistent storage, and any number of plug-in agents, which provide other services such as interfacing with native Linux networking mechanisms, external devices, or SDN controllers.

OpenStack Networking is entirely standalone and can be deployed to a dedicated host. If your deployment uses a controller host to run centralized Compute components, you can deploy the Networking server to that specific host instead.
                        <h2>OpenStack VLANs </h2>
                        <p>
                            
                            VLAN is a networking technology that enables a single switch to act as if it was multiple independent switches. Specifically, two hosts that are connected to the same switch but on different VLANs do not see each other’s traffic. OpenStack is able to take advantage of VLANs to isolate the traffic of different tenants, even if the tenants happen to have instances running on the same compute host. Each VLAN has an associated numerical ID, between 1 and 4095. We say “VLAN 15” to refer to the VLAN with a numerical ID of 15.

To understand how VLANs work, let’s consider VLAN applications in a traditional IT environment, where physical hosts are attached to a physical switch, and no virtualization is involved. Imagine a scenario where you want three isolated networks but you only have a single physical switch. The network administrator would choose three VLAN IDs, for example, 10, 11, and 12, and would configure the switch to associate switchports with VLAN IDs. For example, switchport 2 might be associated with VLAN 10, switchport 3 might be associated with VLAN 11, and so forth. When a switchport is configured for a specific VLAN, it is called an access port. The switch is responsible for ensuring that the network traffic is isolated across the VLANs.

Now consider the scenario that all of the switchports in the first switch become occupied, and so the organization buys a second switch and connects it to the first switch to expand the available number of switchports. The second switch is also configured to support VLAN IDs 10, 11, and 12. Now imagine host A connected to switch 1 on a port configured for VLAN ID 10 sends an Ethernet frame intended for host B connected to switch 2 on a port configured for VLAN ID 10. When switch 1 forwards the Ethernet frame to switch 2, it must communicate that the frame is associated with VLAN ID 10.

If two switches are to be connected together, and the switches are configured for VLANs, then the switchports used for cross-connecting the switches must be configured to allow Ethernet frames from any VLAN to be forwarded to the other switch. In addition, the sending switch must tag each Ethernet frame with the VLAN ID so that the receiving switch can ensure that only hosts on the matching VLAN are eligible to receive the frame.

A switchport that is configured to pass frames from all VLANs and tag them with the VLAN IDs is called a trunk port. IEEE 802.1Q is the network standard that describes how VLAN tags are encoded in Ethernet frames when trunking is being used.

Note that if you are using VLANs on your physical switches to implement tenant isolation in your OpenStack cloud, you must ensure that all of your switchports are configured as trunk ports.

It is important that you select a VLAN range not being used by your current network infrastructure. For example, if you estimate that your cloud must support a maximum of 100 projects, pick a VLAN range outside of that value, such as VLAN 200–299. OpenStack, and all physical network infrastructure that handles tenant networks, must then support this VLAN range.

Trunking is used to connect between different switches. Each trunk uses a tag to identify which VLAN is in use. This ensures that switches on the same VLAN can communicate.
                        <h2>Logical architecture</h2>
                        The figure below resumes the logical architecture that we want to build:
                        <center><img heigth="800px" width="800px" src="DOCUMENTATION/arch.png"/></center>
                        <p>
                           The figure below represents the logical network infrastructure of a VLAN. 
                           <center><img heigth="800px" width="800px" src="DOCUMENTATION/step0009.png"/></center>
                        </p>  
                   </p>
                 </div>
                 <div class="jumbotron">
                        <h1  class="display-3"><a href="#Contact" id="Contact">contact</a></h1>
                        <p>
                            Yassine Fadhlaoui and Simon Kürschner are here to provide you with information, answer any question you may have. You can report an error or a bug by simply sending us a mail describing the problem. 
                            <br>
                          
                           <ul>
                               <li><b>Yassine Fadhlaoui:</b> <a href="mailto:yassine.fadhlaoui@congiv.de">yassine.fadhlaoui@congiv.de</a></li>
                               <li><b>Simon Kürschner:</b> <a href="mailto:simon.kuerschner@congiv.de"> simon.kuerschner@congiv.de</a></li>
                               
                           </ul>
                 </div>
                   

            </div>
<style>
code {
  font-family:courier, monospace;
  font-size:14px;
}
</style>
            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/main.js"></script>
            <script src="js/plugins/pace.min.js"></script>
            <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>

    </body>
</html>