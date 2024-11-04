/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Order;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChangeStatusOrder", urlPatterns = {"/changeStatusOrder"})
public class ChangeStatusOrder extends HttpServlet {

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
        String oid_raw = request.getParameter("oid");
        String action = request.getParameter("action");
        OderDAO oDAO = new OderDAO();
        int oid = Integer.parseInt(oid_raw);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = date.format(formatter);

        if (action.equals("accepted")) {
            oDAO.changeOrderStatus(action, oid);
            oDAO.updateAccepteDate(formattedDateTime, oid);
        } else if (action.equals("rejected")) {
            oDAO.changeOrderStatus(action, oid);
            oDAO.updateRejected(formattedDateTime, oid);
        }
        action = "wait";
        request.setAttribute("action", action);
        response.sendRedirect("selectOrderbyStatus?action=" + action);
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
        String oid_raw = request.getParameter("oid");
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        OderDAO oDAO = new OderDAO();
        int oid = Integer.parseInt(oid_raw);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        if (code.trim() == null || code.trim().isEmpty()) {
            action = "accepted";
            request.setAttribute("action", action);
            request.setAttribute("error", "Tracking code not empty");
            request.getRequestDispatcher("selectOrderbyStatus?action=").forward(request, response);
        } else {
            if (action.equals("intransit")) {
                oDAO.changeOrderStatus(action, oid);
                oDAO.addTrackingCode(code, oid);
                oDAO.updateIntransitDate(formattedDateTime, oid);
            }
            action = "accepted";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
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
