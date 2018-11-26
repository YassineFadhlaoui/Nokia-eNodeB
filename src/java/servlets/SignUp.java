/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.User;
import helpers.Hash;
import helpers.MailSenderEngine;
import helpers.MailValidator;
import helpers.PassValidator;
import helpers.ValidationToken;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
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
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @EJB
    private UserFacade userFacade;
   
    private static final String ConfirmationServlet = "Confirmation";
    public static final String SIGNUP_PAGE = "/LOGIN/signup.jsp";
    private static final String NEWUSER_PAGE="/LOGIN/newuser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //verify
        if (username.length() < 1) {
            request.setAttribute("error", "Username should not be empty");
            this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
            
        } else if (password.length() < 8) {
            request.setAttribute("error", "password should be at least 8 characters long");
            this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
            
        } else if (!new MailValidator(email).isValid()) {
            request.setAttribute("error", "mail is not valid please use a correct congiv mail");
            this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
            
        } else if (!PassValidator.ValidatePass(password).isEmpty()) {
            
            List<String> errors = PassValidator.ValidatePass(password);
            request.setAttribute("error", errors.get(0));
            this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
        } else {
            //everything ok with the form
            //verify that the username is unique
            User user = userFacade.find(username);
            if (user != null) {
                //username is already taken
                System.out.println(user.getUsername());
                request.setAttribute("error", "Sorry the username that you entered is already taken.");
                this.getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(request, response);
            } else {
                //all above condictions are verified 
                //create user
                String RandomToken = ValidationToken.NewUserTokenGenerator();
                System.out.println(RandomToken);
                User newUser = new User(username, email, Hash.hashPassword(password), false, RandomToken);
                
                MailSenderEngine mailSender = new MailSenderEngine();
                try {
                    userFacade.create(newUser);
                    String ServerIP = mailSender.URLBuilder();
                    String uri = request.getContextPath();
                    Vector <String> vector = new Vector();
                    vector.add("GLASSFISH_PORT");
                    String serverPort=helpers.Config.getProperties(vector).get(0);
                    
                    mailSender.generateAndSendEmail(email, "http://" + ServerIP + ":" + serverPort+ uri + "/" + ConfirmationServlet + "?username="+username+"&token=" + RandomToken, username);
                    request.setAttribute("username", username);
                    request.setAttribute("email", email);
                    this.getServletContext().getRequestDispatcher(NEWUSER_PAGE).forward(request, response);
                   
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
