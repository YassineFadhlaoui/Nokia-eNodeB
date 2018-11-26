/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.ProtectClass;
import helpers.SystemMonitoringEngine;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import networks.Network;
import networks.neutron;
import servers.Server;
import servers.nova;

/**
 *
 * @author yassine
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {
    private static final String HOME_PAGE = "/HOME/Home.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isValid=false;
        HttpSession session  = request.getSession();
        if(new ProtectClass().verifyUser(request, response,session, isValid)){
            //User is valid and accessing the page ligitimately
             // servers
             
        nova NovaEngine = new nova();
        List<Server> ServersList = NovaEngine.GetServers();
        int Nservers=0;
        
        if(!ServersList.isEmpty()){
            for(Server server : ServersList)
                if(server.getStatus().equals("ACTIVE")) 
                    Nservers+=1;
        }
        request.setAttribute("Nservers",Nservers);
        // network
        neutron NeutronEngine = new neutron();
        List<Network> VLANs = NeutronEngine.GetNetworks();
        int Nvlans=0;
        if(!VLANs.isEmpty()){
            for(Network vlan : VLANs )
                if(vlan.getType().equalsIgnoreCase("VLAN")) 
                    Nvlans+=1;
        }
        request.setAttribute("Nvlans",Nvlans);
        //memory
       SystemMonitoringEngine sme = new SystemMonitoringEngine();
       
       String Memory= SystemMonitoringEngine.MemoryProcess();
       float FreeMem= Float.NaN;
       float UsedMem= Float.NaN;
       float AvailableMem=Float.NaN;
       if(!Memory.isEmpty()){
           try{
           String [] memory = Memory.split(" ");
           String TotalMemory=memory[1];
           String UsedMemory=memory[2];
           String FreeMemory=memory[3];
           String AvailableMemory= memory[6];
           
           
           Double TM = Double.parseDouble(TotalMemory);
           Double UM= Double.parseDouble(UsedMemory);
           Double FM= Double.parseDouble(FreeMemory);
           Double AM= Double.parseDouble(AvailableMemory);
           
           FreeMem= (float) (FM *100 / TM);
           UsedMem= (float) (UM * 100/ TM);
           AvailableMem = (float)(AM*100/TM);
           float TotalMemoryInGB = (float) (TM /(1024*1024)); 
           // Round 
           String RoundedFreeMem= new DecimalFormat("#.#").format(FreeMem);
           String RoundedUsedMem = new DecimalFormat("#.#").format(UsedMem);
           String RoundedAvailableMem= new DecimalFormat("#.#").format(AvailableMem);
           String RoundedTotalMem= new DecimalFormat("#.##").format(TotalMemoryInGB);
           
           
           request.setAttribute("FreeMem", RoundedFreeMem);
           request.setAttribute("UsedMem", RoundedUsedMem);
           request.setAttribute("AvailableMem", RoundedAvailableMem);
           request.setAttribute("TotalMem", RoundedTotalMem);
           
           //CPU LOAD
           
           String CpuLoad = SystemMonitoringEngine.CPUAverage();
           request.setAttribute("cpuLoad", CpuLoad);
           }catch(Exception ex){
           //Umbrella exception
           System.out.println(ex.toString());
           }
           
           
           
       }
        request.setAttribute("username", session.getAttribute("username"));
        this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
        
        }else{
        response.sendRedirect("/DashboardManagement/Login");
        }
       
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
