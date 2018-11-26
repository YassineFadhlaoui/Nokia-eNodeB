/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Heat.HeatStack;
import Heat.stack;
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
@WebServlet(name = "listVlans", urlPatterns = {"/listVlans"})
public class listVlans extends HttpServlet {
/**
 * This servlet displays all running VLANS First it the server connects to the neutron endpoint and retrieves the information about all networks.
 * Then, it Keeps VLANS. Then it connects to the heat endpoint and retrieves information about different stacks. Then it maps the stack and it's corresponding VLAN.
 */

    private static final String VLAN_LIST = "/VLANs/VLANList.jsp";

    /**
     *This method displays a list of available OpenStack VLANs
     * We start by verifying that the user has a valid session using the helper class Protect Class
     * Once the session verification is done we try to connect to the neutron endpoint and retrieve VLANS information 
     * We use the GetNetworks() method from the class neutron. 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
            neutron neutronEngine = new neutron();
            List<Network> Networks = neutronEngine.GetNetworks();

            List<VLAN> VLANs = new ArrayList();
            if (!Networks.isEmpty()) {
                Server nullInstance = new Server(null, null, null, null, null, null);
                HeatStack stack = new HeatStack(null, null, null, null);

                for (Network network : Networks) {
                    if (network.getType().equalsIgnoreCase("VLAN")) {
                        VLANs.add(new VLAN(network, nullInstance, stack));
                    }
                }
            }
            if (!VLANs.isEmpty()) {
                try{
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
                            }
                        }
                    }
                }
            }catch(Exception e){
                System.out.println(e);
                    }
            }
            //for(VLAN vlan: VLANs) System.out.println(vlan);
            request.setAttribute("VLANs", VLANs);
            request.setAttribute("username", session.getAttribute("username"));
            this.getServletContext().getRequestDispatcher(VLAN_LIST).forward(request, response);

        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return Get VLANs list created using the OpenStack platform. It initiates get requestes to the Openstack neutron and heat endpoints and retrieve information about different created VLANS
     */
    @Override
    public String getServletInfo() {
        return "Get VLANs list created using the OpenStack platform. It initiates get requestes to the Openstack neutron and heat endpoints and retrieve information about different created VLANS";
    }// </editor-fold>

}
