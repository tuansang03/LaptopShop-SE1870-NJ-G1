/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchOrder", urlPatterns = {"/searchOrder"})
public class SearchOrder extends HttpServlet {

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
        String search = request.getParameter("searchOrder");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if((search == null || search.isEmpty()) 
            && (startDate == null || startDate.isEmpty()) 
            && (endDate == null || endDate.isEmpty())){
        //dung yen
        }else if((startDate == null || startDate.isEmpty()) 
            && (endDate == null || endDate.isEmpty())){
            //Search
        }else if((search == null || search.isEmpty()) 
            && (endDate == null || endDate.isEmpty())) {
            //Start
        }else if((search == null || search.isEmpty())
            && (startDate == null || startDate.isEmpty())) {
            //End
        }else if((endDate == null || endDate.isEmpty())) {
            //Search + Start
        }else if((startDate == null || startDate.isEmpty())) {
            //Search + End
        }else if((search == null || search.isEmpty())) {
            //Start + End
        } else {
            //ALL
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
