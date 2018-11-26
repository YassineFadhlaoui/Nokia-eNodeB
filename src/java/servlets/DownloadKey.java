/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Template.RSAKeyGen;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yassine
 */
@WebServlet(name = "DownloadKey", urlPatterns = {"/DownloadKey"})
public class DownloadKey extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        String PathToRSA = new RSAKeyGen().CreateKeyStoreDir() + id + ".pem";
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + id + ".pem\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            byte[] KeyBytes = Files.readAllBytes(new File(PathToRSA).toPath());
            outputStream.write(KeyBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
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
