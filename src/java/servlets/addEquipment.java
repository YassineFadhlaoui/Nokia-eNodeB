/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.ProtectClass;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.EquipmentFacade;

/**
 *
 * @author yassine
 */

/*
Boolean isValid = false;
        HttpSession session = request.getSession();
if (new ProtectClass().verifyUser(request, response, session, isValid)) {
             
        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
*/
@WebServlet(name = "addEquipment", urlPatterns = {"/addEquipment"})
public class addEquipment extends HttpServlet {

    @EJB
    private EquipmentFacade equipmentEM;
    private static final String ADD_EQUIPMENT = "/VLANs/addEquipment.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
             request.setAttribute("username", session.getAttribute("username"));
        this.getServletContext().getRequestDispatcher(ADD_EQUIPMENT).forward(request, response);
        } else {
            response.sendRedirect("/DashboardManagement/Login");
        
        }
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
if (new ProtectClass().verifyUser(request, response, session, isValid)) {
                     String Error = "";
        String scon = request.getParameter("scon");
        String sdp = request.getParameter("sdp");
        String segID = request.getParameter("segID");
        String tcn = request.getParameter("tcn");
        String tdp = request.getParameter("tdp");
        String status = request.getParameter("status");
        String comment = request.getParameter("comment");

        if (segID == null || comment == null || segID.isEmpty() || comment.isEmpty()) {
            Error = "VLAN and Comment fields must not be empty";
        }
        if (!Error.isEmpty()) {
            request.setAttribute("Error", Error);
            request.setAttribute("username", session.getAttribute("username"));
            this.getServletContext().getRequestDispatcher(ADD_EQUIPMENT).forward(request, response);
        } else {
            entities.Equipment equipment = new entities.Equipment();
            equipment.setSourceCONumber(scon);
            equipment.setSourcedevicePort(sdp);
            equipment.setVlanid(segID);
            equipment.setDestinationCONumber(tcn);
            equipment.setDestinationCONumber(tdp);
            equipment.setStatus(status);
            equipment.setComment(comment);

            equipmentEM.create(equipment);

        }
        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
