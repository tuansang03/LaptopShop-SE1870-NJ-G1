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
import java.sql.Date;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="EditVoucher", urlPatterns={"/editVoucher"})
public class EditVoucher extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_raw = request.getParameter("id");
        int id = Integer.parseInt(id_raw);
        
        VoucherDAO vDAO = new VoucherDAO();
        
        Voucher voucher = vDAO.getVoucherByID(id);
        
        request.setAttribute("voucher", voucher);
        request.getRequestDispatcher("editVoucherDisplay.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String discount_raw = request.getParameter("discount");
        String quantity_raw = request.getParameter("quantity");
        String startdate_raw = request.getParameter("startdate");
        String enddate_raw = request.getParameter("enddate");
        String minvalue_raw = request.getParameter("minvalue");
        String discountcap_raw = request.getParameter("discountcap");
        String status = request.getParameter("status");
    
        VoucherDAO vDAO = new VoucherDAO();
        int id = Integer.parseInt(id_raw);
        int discount = Integer.parseInt(discount_raw);
        int quantity = Integer.parseInt(quantity_raw);
        Date startDate = Date.valueOf(startdate_raw);
        Date endDate = Date.valueOf(enddate_raw);
        int minvalue = Integer.parseInt(minvalue_raw);
        int discountCap = Integer.parseInt(discountcap_raw);
        
        vDAO.editVoucher(id, code, name, discount, quantity, 
                startDate, endDate, minvalue, discountCap, status);
    response.sendRedirect("voucherManager");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
