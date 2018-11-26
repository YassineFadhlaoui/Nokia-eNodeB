/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Template.TemplateEngine;
import helpers.AccessToken;
import helpers.ProtectClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import networks.Network;
import networks.neutron;
import org.json.JSONObject;

/**
 *
 * @author yassine
 */
@WebServlet(name = "newVlan", urlPatterns = {"/newVlan"})
public class newVlan extends HttpServlet {

    private static final String VLAN_MANAGEMENT_PAGE = "/VLANs/newVLAN.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
            String VlanName = request.getParameter("vlan");
            String VlanID = request.getParameter("vlanid");
            if (!(VlanName == null || VlanID == null)) {
                request.setAttribute("VlanName", VlanName);
                request.setAttribute("VlanID", VlanID);
            }
            request.setAttribute("username", session.getAttribute("username"));
            this.getServletContext().getRequestDispatcher(VLAN_MANAGEMENT_PAGE).forward(request, response);
        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
    }

    private void createVLAN(HttpServletRequest request, HttpServletResponse response, HttpSession session, String segID) {
        Vector<String> Parameters = new Vector();
        Parameters.add("OPENSTACK_SERVER_IP");
        Parameters.add("TENANT_ID");
        Parameters.add("HEAT_ENDPOINT_PORT");
        Parameters.add("HEAT_API_VERSION");

        Vector<String> params = helpers.Config.getProperties(Parameters);
        String openstackServer = params.get(0);
        String tenantID = params.get(1);
        String heatEndpointPort = params.get(2);
        String heatApiVersion = params.get(3);

        String equipement = request.getParameter("equipement");
        //change the name of the vlan to equipment-user-input for internal usage

        String vlanName = equipement + "_" + segID;
        System.out.println("here");
        neutron Neutron = new neutron();
        List<Network> Networks = Neutron.GetNetworks();
        Boolean notFound = true;
        for (Network net : Networks) {
            if (net.getType().equalsIgnoreCase("vlan") && net.getSegmentationID().equals(segID) && (net.getName().startsWith(equipement.toUpperCase()) || net.getName().startsWith(equipement.toLowerCase()))) {
                notFound = false;
                response.setStatus(400);
                break;
            }
        }
        System.out.println(notFound);
        //System.out.println(vlanName+" "+segID+" "+equipement+" "+multithreading);
        try {

            if (notFound) {
                System.out.println("creation will start");
                Template.TemplateEngine t = new TemplateEngine();
                System.out.println("initiation ...");
                JSONObject HttpBody = t.GenerateTemplate(equipement, vlanName, segID);
                String error = t.getError();
                System.out.println(error);
                try {
                    AccessToken token = new AccessToken();
                    String Token = token.RequestAccessToken();
                    //String EndPointURL = "http://" + IP_ADDRESS + ":" + PORT + "/" + VERSION + "/" + ENDPOINT;
                    String EndPointURL = "http://" + openstackServer + ":" + heatEndpointPort + "/" + heatApiVersion + "/" + tenantID + "/" + "stacks";

                    URL url = new URL(EndPointURL);
                    URLConnection connection = url.openConnection();
                    HttpURLConnection http = (HttpURLConnection) connection;
                    http.setRequestMethod("POST");

                    String correct = "{\"files\": {}, \"disable_rollback\": true, \"parameters\": {}, \"stack_name\": \"STACK_NAME\", \"environment\": {}, \"template\": TEMPLATE_BODY }";
                    String Template = correct.replace("TEMPLATE_BODY", HttpBody.toString());

                    String StackName = "Stack_VLAN_" + segID + "_" + equipement;
                    String Final = Template.replace("STACK_NAME", StackName);
                    
                    byte[] bson = Final.getBytes(StandardCharsets.UTF_8);

                    http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    //X-OpenStack-Nova-API-Version: 2.53
                    //http.setRequestProperty("X-OpenStack-Nova-API-Version", "2.53");
                    System.out.println("token: "+Token+" ;"+ "JSON:\n"+Final);
                    http.setRequestProperty("X-Auth-Token", Token);
                    http.setDoOutput(true);
                    http.connect();
                    OutputStream os = http.getOutputStream();
                    os.write(bson);

                    InputStream is = http.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    JSONObject output = new JSONObject(sb.toString());
                    System.out.println(output);
                    response.sendRedirect("/DashboardManagement/listVlans");
                } catch (Exception e) {
                    System.out.println(e);

                }

                if (!error.isEmpty()) {
                    System.out.println("not able to get management network name");
                }

            } else {

                request.setAttribute("error", "A vlan with the same segmentation ID is already created for this equipment type please destroy it or use an other segmentation ID");
                request.setAttribute("username", session.getAttribute("username"));
                this.getServletContext().getRequestDispatcher(VLAN_MANAGEMENT_PAGE).forward(request, response);

            }

        } catch (Exception e) {
            try {
                System.out.println(e);
                //flat network problem
                //ich werde das schaffen
                response.sendRedirect("/DashboardManagement/Error");
            } catch (IOException ex) {
                Logger.getLogger(newVlan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {

            //get segmentation ID list
            String segID = request.getParameter("segID");
            //System.out.println(segID);
            String[] vlansIds = segID.split(",");
           // for(String id: vlansIds) System.out.println(id);
           Set <String> VLANids = new LinkedHashSet<>();
            for (String item : vlansIds) {
                if (item.contains("-")) {
                    String [] consecutiveVLANs= item.split("-");
                    if(consecutiveVLANs.length != 2 ){
                        System.out.println("input error");
                    }else{
                        String lowerID= consecutiveVLANs[0];
                        String upperID= consecutiveVLANs[1];
                        try{
                        int lowerIDvalue = Integer.parseInt(lowerID);
                        int upperIDvalue = Integer.parseInt(upperID);
                        
                        for (int index=lowerIDvalue; index<=upperIDvalue; index++){
                           // createVLAN(request, response, session, String.valueOf(item));
                           //System.out.println("vlan with id "+ index+" will be created");
                           VLANids.add(String.valueOf(index));
                            
                        }
                        }catch(Exception e){
                        System.out.println("parsing error");
                        }
                    }
                } else {
                    try {
                        int id = Integer.parseInt(item);
                        System.out.println(id);
                       // createVLAN(request, response, session, String.valueOf(id));
                      // System.out.println("vlan with"+id+" will be created");
                       VLANids.add(item);
                    } catch (Exception e) {
                        System.out.println("parse error"+ e);
                    }
                }
            };
            //VLANids list filtred non-duplicated vlan ids :)
            //list them
            for (String vlanid: VLANids) {
                try{
                   System.out.println(vlanid);
                    createVLAN(request, response, session, vlanid);
                }catch(Exception e){
                    System.out.println(e);
                }
            }//System.out.println(vlanid);

        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
