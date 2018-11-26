package servlets;

import entities.User;
import helpers.Hash;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    private static final String LOGIN_PAGE = "/LOGIN/login.jsp";
    private static final String ACTIVATE_PAGE = "/LOGIN/activateAccount.jsp";
    //private static final String COOKIE_PAGE="/LOGIN/cookiesError.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = null;
        //start verfying if the user has an active session
        request.setAttribute("error", "");
        if (!session.isNew()) {
            try {
                username = (String) session.getAttribute("username");
                if (username != null) {
                    System.out.println("session active");
                    response.sendRedirect("/DashboardManagement/Home");

                } else {
                    this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                }
            } catch (Exception e) {
                this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);

        }
        //}

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
        String Username = request.getParameter("username");
        String Password = request.getParameter("password");

        if (Username.length() < 1 || Password.length() < 1) {
            request.setAttribute("error", "Username and Password should not be empty");
            this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else {

            User user = userFacade.find(Username);
            if (user != null) {
                System.out.println("Password: "+new  String(Hash.hashPassword(Password).getBytes( "utf-8" ),"utf-8")+" real: "+ user.getPassword());
                if (Hash.hashPassword(Password).equalsIgnoreCase(user.getPassword())) {
                    //correct login and password
                    //create cookies and session
                    //verify if the account is confirmed
                    if (user.getIsActive() == false) {
                        request.setAttribute("username", Username);
                        request.setAttribute("email", user.getEmail());
                        this.getServletContext().getRequestDispatcher(ACTIVATE_PAGE).forward(request, response);
                    } else {//correct Login and password and valid account
                        //create session and cookies
                        HttpSession session;
                        session = request.getSession();
                        session.setAttribute("username", user.getUsername());
                        // session.setAttribute("email", user.getEmail());

                        System.out.println("session created");
                        response.sendRedirect("/DashboardManagement/Home");
                    }

                } else {
                    //correct username but wrong password
                    request.setAttribute("error", "Username or/and password was wrong");
                    this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);

                }

            } else {
                request.setAttribute("error", "Username is not found please create new account");
                this.getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
