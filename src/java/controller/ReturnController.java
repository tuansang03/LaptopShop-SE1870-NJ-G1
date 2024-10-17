/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OderDAO;
import dal.OrdeiDetailDAO;
import dal.ReturnDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;
import model.Return;
import model.ReturnDetail;
import model.User;

/**
 *
 * @author LocPham
 */
@WebServlet(name = "ReturnController", urlPatterns = {"/ReturnController"})
public class ReturnController extends HttpServlet {

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
            out.println("<title>Servlet ReturnController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturnController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User getCurrentUser = (User) session.getAttribute("user");
        OderDAO order = new OderDAO();
        String service = request.getParameter("service");

        if (service.equalsIgnoreCase("returnRequest")) {
            String orderId_raw = request.getParameter("id");
            int orderId = Integer.parseInt(orderId_raw);
//            session.setAttribute("orderId", orderId);
            List<OrderDetail> list = new ArrayList<>();
            try {
                OrdeiDetailDAO detailDAO = new OrdeiDetailDAO();
                List<OrderDetail> listOrderDetail = new ArrayList<>();
                listOrderDetail = detailDAO.getOrderDetailByOrderId(orderId);
                session.setAttribute("listReturn", listOrderDetail);
                list = order.getOrderDetailsByUserAndOrder(getCurrentUser.getId(), orderId);
                request.setAttribute("orderId", orderId);
                request.setAttribute("listOrderDetail", list);
                request.getRequestDispatcher("returndisplay.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ReturnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (service.equalsIgnoreCase("returnDone")) {
            String orderId_raw = request.getParameter("orderId");
            int orderId = Integer.parseInt(orderId_raw);
            String money_raw = request.getParameter("money");
            int money = Integer.parseInt(money_raw);
            String reason = request.getParameter("reason");
            String refundMethod = request.getParameter("refundMethod");
            LocalDateTime currentReturnDate = LocalDateTime.now();
            OderDAO dao = new OderDAO();
            Order o = dao.getOrderById(orderId);
            Return return1 = new Return(money, reason, refundMethod, "wait", "wait", o, currentReturnDate);
            ReturnDAO returnDao = new ReturnDAO();
            returnDao.insertReturn(return1);
//            ReturnDetail returnDetail = new ReturnDetail(return1.getId(),);
            ReturnDAO returnDAO = new ReturnDAO();
            OrdeiDetailDAO detailDAO = new OrdeiDetailDAO();
            List<OrderDetail> listOrderDetail = new ArrayList<>();
            List<ReturnDetail> listReturnDetail = new ArrayList<>();
            listOrderDetail = detailDAO.getOrderDetailByOrderId(orderId);
            for (OrderDetail orderDetail : listOrderDetail) {
                int quantity = Integer.parseInt(request.getParameter("orderDetailId_" + orderDetail.getId()));
                System.out.println("quantity " + quantity);
                if(quantity > 0){
                    int price = quantity * orderDetail.getUnitPrice();
                    ReturnDetail r = new ReturnDetail(return1, orderDetail, quantity, price);
                    returnDAO.insertReturnDetail(r);
                }
            }
//            List<Integer> listOrderDetaiId = new ArrayList<>();
//            listOrderDetaiId = detailDAO.getOrderDetailIdByOrderId(orderId);
//            for (Integer integer : listOrderDetaiId) {
//                String i = integer+"";
//                request.getAttribute(i);
//            }
//            List<OrderDetail> listOrderDetail = new ArrayList<>(); 
//            listOrderDetail = detailDAO.getOrderDetailByOrderId(orderId);

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
