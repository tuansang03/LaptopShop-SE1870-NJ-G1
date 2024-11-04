/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OderDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Order;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchAndGetStatus", urlPatterns = {"/searchAndGetStatus"})
public class SearchAndGetStatus extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sale");
        UserDAO uDAO = new UserDAO();
        int saleid = uDAO.getUserByIdD(user.getId()).getId();
        
        List<Order> listOrderOfSearch = (List<Order>) request.getAttribute("listOrderOfSearch");

        String search = (String) request.getAttribute("search");
        String endDate = (String) request.getAttribute("endDate");
        String startDate = (String) request.getAttribute("startDate");

        String action = request.getParameter("action");

        OderDAO oDAO = new OderDAO();
        String op = "";
        if (action.equals("wait")) {
            op = "wait";
        } else if (action.equals("rejected")) {
            op = "rejected";
        } else if (action.equals("accepted")) {
            op = "accepted";
        } else if (action.equals("intransit")) {
            op = "intransit";
        } else if (action.equals("failed")) {
            op = "failed";
        } else if (action.equals("done")) {
            op = "done";
        }
        //List<Order> listOrder = oDAO.getAllOrder(op);

        int totalAmountWait = oDAO.totalAmountByOrderStatus("wait", saleid);
        int totalAmountRejected = oDAO.totalAmountByOrderStatus("rejected", saleid);
        int totalAmountAccepted = oDAO.totalAmountByOrderStatus("accepted", saleid);
        int totalAmountIntransit = oDAO.totalAmountByOrderStatus("intransit", saleid);
        int totalAmountFailed = oDAO.totalAmountByOrderStatus("failed", saleid);
        int totalAmountDone = oDAO.totalAmountByOrderStatus("done", saleid);

        int totalOrderWait = oDAO.totalOrderByOrderStatus("wait", saleid);
        int totalOrderRejected = oDAO.totalOrderByOrderStatus("rejected", saleid);
        int totalOrderAccepted = oDAO.totalOrderByOrderStatus("accepted", saleid);
        int totalOrderIntransit = oDAO.totalOrderByOrderStatus("intransit", saleid);
        int totalOrderFailed = oDAO.totalOrderByOrderStatus("failed", saleid);
        int totalOrderDone = oDAO.totalOrderByOrderStatus("done", saleid);

        request.setAttribute("totalAmountWait", totalAmountWait);
        request.setAttribute("totalAmountRejected", totalAmountRejected);
        request.setAttribute("totalAmountAccepted", totalAmountAccepted);
        request.setAttribute("totalAmountIntransit", totalAmountIntransit);
        request.setAttribute("totalAmountFailed", totalAmountFailed);
        request.setAttribute("totalAmountDone", totalAmountDone);

        request.setAttribute("totalOrderWait", totalOrderWait);
        request.setAttribute("totalOrderRejected", totalOrderRejected);
        request.setAttribute("totalOrderAccepted", totalOrderAccepted);
        request.setAttribute("totalOrderIntransit", totalOrderIntransit);
        request.setAttribute("totalOrderFailed", totalOrderFailed);
        request.setAttribute("totalOrderDone", totalOrderDone);

        //request.setAttribute("action", action);
        //request.setAttribute("listOrder", listOrder);
        Map<Integer, String> orderStatusMap = new HashMap<>();

        for (Order order : listOrderOfSearch) {
            orderStatusMap.put(order.getId(), order.getOrderStatus());
        }

//            for (Map.Entry<Integer, String> entry : orderStatusMap.entrySet()) {
//                System.out.println("Order ID: " + entry.getKey() + ", Status: " + entry.getValue());
//            }
        request.setAttribute("orderStatusMap", orderStatusMap);
        request.setAttribute("listOrder", listOrderOfSearch);
        request.setAttribute("search", search);
        request.setAttribute("endDate", endDate);
        request.setAttribute("startDate", startDate);
        request.getRequestDispatcher("managerOrderDisplay2.jsp").forward(request, response);

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
