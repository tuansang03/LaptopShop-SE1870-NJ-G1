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
        response.setContentType("text/html;charset=UTF-8");

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
        String opPaymentStatus = request.getParameter("opPaymentStatus");
        String oid_raw = request.getParameter("oid");
        int oid = Integer.parseInt(oid_raw);

        OderDAO oDAO = new OderDAO();

        oDAO.changePaymentStatus(opPaymentStatus, oid);
        response.sendRedirect("managerOrder");
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
        String opOrderStatus = request.getParameter("opOrderStatus");
        String oid_raw = request.getParameter("oid");
        int oid = Integer.parseInt(oid_raw);

        OderDAO oDAO = new OderDAO();

        Order order = oDAO.getOrderByID(oid);

        if (order.getPaymentMethod().equals("Payment")
                && (order.getPaymentStatus().equals("fail")
                || order.getPaymentStatus().equals("pending"))) {
            
            request.setAttribute("error", "The failed or pending payment status cannot be changed to Accepted or Done");
            request.getRequestDispatcher("managerOrder").forward(request, response);
            return;
        }

        oDAO.changeOrderStatus(opOrderStatus, oid);

        request.setAttribute("oid", oid);
        request.getRequestDispatcher("managerOrder").forward(request, response);
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
