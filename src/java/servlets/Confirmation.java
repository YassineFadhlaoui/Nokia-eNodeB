/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessions.UserFacade;

/**
 *
 * @author yassine
 */
@WebServlet(name = "Confirmation", urlPatterns = {"/Confirmation"})
public class Confirmation extends HttpServlet {

    private static final String ERROR_PAGE = "/ERRORS/error.jsp";
    private static final String REDIRECT_PAGE = "/LOGIN/redirect.jsp";

    @EJB
    private UserFacade userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String SecurityToken = request.getParameter("token");
        String username = request.getParameter("username");

        System.out.println(SecurityToken);
        User user = userFacade.find(username);
        if (user == null) {
            // System.out.println("error");
            request.setAttribute("error", "Malformed or manipulated url detected. Spoofing attempt Blocked");
            this.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } else if (!user.getActivationToken().equals(SecurityToken)) {
            request.setAttribute("error", "Manipulated token detected. Spoofing attempt Blocked");
            this.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } else {
            user.setIsActive(true);
            userFacade.edit(user);
            this.getServletContext().getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
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
