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
import sessions.UserFacade;

/**
 *
 * @author yassine
 */
@WebServlet(name = "myprofile", urlPatterns = {"/myprofile"})
public class myprofile extends HttpServlet {

    @EJB
    UserFacade userFacade;
    private static final String PROFILE_PAGE = "/PROFILE/myprofile.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
            request.setAttribute("username", session.getAttribute("username"));
            this.getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        } else {
            response.sendRedirect("/DashboardManagement/Login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");

        Boolean isValid = false;
        HttpSession session = request.getSession();
        if (new ProtectClass().verifyUser(request, response, session, isValid)) {
            String user = session.getAttribute("username").toString();
            entities.User User = userFacade.find(user);
            if (User != null) {
                if (!User.getPassword().equals(helpers.Hash.hashPassword(oldPass))) {
                    request.setAttribute("Error", "old password is not correct");
                    this.getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(request, response);

                } else if (!helpers.PassValidator.ValidatePass(newPass).isEmpty()) {
                    request.setAttribute("Error", helpers.PassValidator.ValidatePass(newPass).get(0));
                    this.getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                } else {
                    //alles gut
                    User.setPassword(helpers.Hash.hashPassword(newPass));
                    userFacade.edit(User);
                    System.out.println("password updated");
                    this.getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                }

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
