/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchVoucher", urlPatterns = {"/searchVoucher"})
public class SearchVoucher extends HttpServlet {

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
        String search = request.getParameter("search");
        String op = request.getParameter("op");
        VoucherDAO vDAO = new VoucherDAO();

        String status = "";
        if (op.equals("expired")) {
            status = "0";
        }else if(op.equals("active")) {
            status = "1";
        }
        
        List<Voucher> listVoucher = null;
        if (op.equals("all")) {
            //find name
            listVoucher = vDAO.searchVoucherByName(search, "", "all");
        } else if (!(search.isEmpty()) && (op.equals("active") || op.equals("expired"))) {
            //find status + name
            listVoucher = vDAO.searchVoucherByName(search, status, op);
        }else {
            //find status
            listVoucher = vDAO.searchVoucherByName("noName", status, op);
        }

        request.setAttribute("op", op);
        request.setAttribute("search", search);
        request.setAttribute("listVoucher", listVoucher);
        request.getRequestDispatcher("manageVoucherDisplay.jsp").forward(request, response);
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
