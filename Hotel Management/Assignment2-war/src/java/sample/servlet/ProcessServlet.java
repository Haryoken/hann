/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author haryoken
 */
public class ProcessServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String loginServlet = "LoginServlet";
    private final String showAllServlet = "ShowAllServlet";
    private final String searchServlet = "SearchServlet";
    private final String changeServlet = "ChangeServlet";
    private final String saveServlet = "SaveServlet";
    private final String requestPage = "request.jsp";
    private final String reportServlet = "ReportServlet";
    private final String filterServlet = "FilterServlet";
    private final String reportUpdateServlet = "UpdateReportServlet";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String button = request.getParameter("btAction");
            String url = loginPage;
            
            if (button == null) {             
                url = loginPage;
            } else if (button.equals("Login")) {
                url = loginServlet;
            } else if (button.equals("Show All")) {
                url = showAllServlet;
            } else if (button.equals("Search")) {              
                url = searchServlet;
            } else if (button.equals("Change")) {
                url = changeServlet;
            } else if (button.equals("Save")) {
                url = saveServlet;
            } else if (button.equals("Request")) {
                url = requestPage;
            } else if (button.equals("Report")) {
                url = reportServlet;
            } else if (button.equals("Filter")) {
                url = filterServlet;
            } else if (button.equals("Finish")) {
                url = reportUpdateServlet;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } finally {
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
