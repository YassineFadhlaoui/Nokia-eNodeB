/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Heat.HeatStack;
import Heat.stack;
import Template.RSAKeyGen;
import helpers.ProtectClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import networks.Network;
import networks.VLAN;
import networks.neutron;
import servers.Server;
import servers.nova;

/**
 *
 * @author yassine
 */
@WebServlet(name = "DeleteStack", urlPatterns = {"/DeleteStack"})
public class DeleteStack extends HttpServlet {

    private static final String VLAN_LIST = "/VLANs/VLANList.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String RSAKeyName = request.getParameter("key");

            //authenticate and delete
            stack Stack = new stack();
            Stack.deleteStacks(name, id);
            RSAKeyGen rsa = new RSAKeyGen();
            rsa.RevokeRSAKey(RSAKeyName);
            //update view 
            neutron neutronEngine = new neutron();
            List<Network> Networks = neutronEngine.GetNetworks();

            List<VLAN> VLANs = new ArrayList();
            if (!Networks.isEmpty()) {
                Server nullInstance = new Server(null, null, null, null, null, null,null);
                HeatStack stack = new HeatStack(null, null, null, null);

                for (Network network : Networks) {
                    if (network.getType().equalsIgnoreCase("VLAN")) {
                        VLANs.add(new VLAN(network, nullInstance, stack));
                    }
                }
            }
            if (!VLANs.isEmpty()) {
                try {
                    nova novaEngine = new nova();
                    List<Server> instances = novaEngine.GetServers();
                    List<HeatStack> stacks = new stack().getStacks();

                    for (Server instance : instances) {
                        for (VLAN vlan : VLANs) {
                            if (instance.getVlanID().equalsIgnoreCase(vlan.getVlan().getSegmentationID())) {
                                vlan.setInstance(instance);

                            }
                        }
                        for (HeatStack stack : stacks) {
                            for (VLAN vlan : VLANs) {
                                String VLANID = "0";
                                if (!stack.getVLAN().isEmpty()) {
                                    String[] parts = stack.getVLAN().split("-");
                                    VLANID = parts[parts.length - 3];
                                    System.out.println(VLANID);
                                }

                                if (VLANID.equalsIgnoreCase(vlan.getVlan().getSegmentationID())) {
                                    vlan.setStack(stack);
                                    System.out.println("here");
                                    System.out.println(vlan);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
            //for(VLAN vlan: VLANs) System.out.println(vlan);
            request.setAttribute("dStackName", name);
            request.setAttribute("dStackid", id);

            request.setAttribute("VLANs", VLANs);
            request.setAttribute("username", session.getAttribute("username"));
            this.getServletContext().getRequestDispatcher(VLAN_LIST).forward(request, response);

        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
