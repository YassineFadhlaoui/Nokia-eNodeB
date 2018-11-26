/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import helpers.ProtectClass;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "Equipment", urlPatterns = {"/Equipment"})
public class Equipment extends HttpServlet {

    @EJB
    private EquipmentFacade equipmentEM;
    private static final String EQUIPMENT_LIST = "/VLANs/equipment.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {

            List<entities.Equipment> EquipmentList = equipmentEM.findAll();
            //for(entities.Equipment equipment : EquipmentList) System.out.println(equipment.getVlanid());
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                try {
                    entities.Equipment eq = equipmentEM.find(Integer.parseInt(id));
                    if (eq != null) {
                        equipmentEM.remove(eq);
                        System.out.println("equipment with ID: " + id + " was successfully deleted");
                        response.sendRedirect("/DashboardManagement/Equipment");
                    }
                    System.out.println("not found.. spoofed request");
                    response.sendRedirect("/DashboardManagement/Equipment");
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                request.setAttribute("EquipmentList", EquipmentList);
                request.setAttribute("username", session.getAttribute("username"));
                this.getServletContext().getRequestDispatcher(EQUIPMENT_LIST).forward(request, response);

            }

        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //new entires 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
