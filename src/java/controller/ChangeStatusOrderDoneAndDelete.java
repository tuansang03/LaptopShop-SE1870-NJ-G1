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

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ChangeStatusOrderDoneAndDelete", urlPatterns = {"/changeStatusOrderDoneAndDelete"})
public class ChangeStatusOrderDoneAndDelete extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeStatusOrderDoneAndDelete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeStatusOrderDoneAndDelete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String oid_raw = request.getParameter("oid");
        String op = request.getParameter("op");

        int oid = Integer.parseInt(oid_raw);
        OderDAO oDAO = new OderDAO();

        oDAO.deleteOrderDetail(oid);
        oDAO.deleteOrder(oid);

        if (op.equals("failed")) {
            String action = "failed";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
        } else if (op.equals("rejected")) {
            String action = "rejected";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
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
        String oid_raw = request.getParameter("oid");
        String action = request.getParameter("action");
        OderDAO oDAO = new OderDAO();
        int oid = Integer.parseInt(oid_raw);
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        if (action.equals("done")) {
            oDAO.changeOrderStatus(action, oid);
            oDAO.updateDoneDate(formattedDateTime, oid);
        } else if (action.equals("failed")) {
            oDAO.changeOrderStatus(action, oid);
            oDAO.updateShipmentFailedDate(formattedDateTime, oid);
        }

        action = "intransit";
        request.setAttribute("action", action);
        response.sendRedirect("selectOrderbyStatus?action=" + action);
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
