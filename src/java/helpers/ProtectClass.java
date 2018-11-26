/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.UserFacade;

/**
 *
 * @author yassine
 */
public class ProtectClass {

    @EJB
    private UserFacade userFacade;

    public Boolean verifyUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, Boolean isValid) throws ServletException, IOException {

        String username = null;

        //start verfying if the user has an active session
        if (!session.isNew()) {
            try {
                username = (String) session.getAttribute("username");
                if (username != null) {
                    System.out.println("session active");
                    //response.sendRedirect("/DashboardManagement/Home");
                    isValid = true;
                } else {
                    isValid = false;
                }
            } catch (Exception e) {
                isValid = false;
            }

        }
        return isValid;
    }
}
