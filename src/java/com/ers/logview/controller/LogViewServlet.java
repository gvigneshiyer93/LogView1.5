/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.controller;

import com.ers.logview.model.LogAction;
import com.ers.logview.model.MasterAction;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author envtraining
 */
public class LogViewServlet extends HttpServlet {

    private Map<String, String> masterMap;

    //private MasterAction action=new MasterAction();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        LogAction logAction;
        MasterAction action;        
        String key = "fetching";
        System.out.println("hello servlet");
        String uri = request.getParameter("uri");
        System.out.println(uri);
        if (uri.equals("/")) {
            key = "fetching";
        } else if (uri.equals("login")) {
            key = "login";
        } else if (uri.equals("auditlog")) {
            key = "auditlog";
        } else if (uri.equals("activitylog")) {
            key = "activitylog";
        } else if (uri.equals("changelog")) {
            key = "changelog";
        } else if (uri.equals("tasklog")) {
            key = "tasks";
        } else if (uri.equals("exceptionlog")) {
            key = "exception";
        } else if (uri.equals("docusignlog")) {
            key = "docusign";
        } else if (uri.equals("maillog")) {
            key = "mail";
        } else {
        }
        if (key.equals("fetching")) {
            action = MasterAction.getInstance();
            action.masterJsonAction(request, response);
        } else {
            logAction = new LogAction();
            String value = masterMap.get(key);
            logAction.logDisplayAction(request, response, value);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LogViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LogViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        MasterAction action = MasterAction.getInstance();
        try {
            masterMap = action.masterMapAction();
            System.out.println("hello init");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        config.getServletContext().setAttribute("masterMap", masterMap);
    }

}
