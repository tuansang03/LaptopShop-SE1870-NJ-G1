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
import java.util.List;
import model.Order;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Order;
import model.User;

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
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sale");
        if (user == null) {
            request.getRequestDispatcher("notallowpage.jsp").forward(request, response);
        }

        UserDAO uDAO = new UserDAO();
        int saleid = uDAO.getUserByIdD(user.getId()).getId();
        
        //String action = request.getParameter("action");
        OderDAO oDAO = new OderDAO();
        List<Order> listOrder = null;
        if((search == null || search.isEmpty()) 
            && (startDate == null || startDate.isEmpty()) 
            && (endDate == null || endDate.isEmpty())){
        //dung yen
        listOrder = oDAO.searchOrderByUserNameAndDate("Null", "Null", "Null", saleid);
        }else if((startDate == null || startDate.isEmpty()) 
            && (endDate == null || endDate.isEmpty())){
            //Search
           listOrder = oDAO.searchOrderByUserNameAndDate(search, "Null", "Null", saleid);
        }else if((search == null || search.isEmpty()) 
            && (endDate == null || endDate.isEmpty())) {
            //Start
           listOrder = oDAO.searchOrderByUserNameAndDate("Null", startDate, "Null", saleid);
        }else if((search == null || search.isEmpty())
            && (startDate == null || startDate.isEmpty())) {
            //End
           listOrder = oDAO.searchOrderByUserNameAndDate("Null", "Null", endDate, saleid);
        }else if((endDate == null || endDate.isEmpty())) {
            //Search + Start
           listOrder = oDAO.searchOrderByUserNameAndDate(search, startDate, "Null", saleid);
        }else if((startDate == null || startDate.isEmpty())) {
            //Search + End
           listOrder = oDAO.searchOrderByUserNameAndDate(search, "Null", endDate, saleid);
        }else if((search == null || search.isEmpty())) {
            //Start + End
           listOrder = oDAO.searchOrderByUserNameAndDate("Null", startDate, endDate, saleid);
        } else {
            //ALL
           listOrder = oDAO.searchOrderByUserNameAndDate(search, startDate, endDate, saleid);
        }
        request.setAttribute("listOrderOfSearch", listOrder);
        
        String action = "wait";
        request.setAttribute("action", action);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("search", search);
        request.getRequestDispatcher("searchAndGetStatus").forward(request, response);
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
