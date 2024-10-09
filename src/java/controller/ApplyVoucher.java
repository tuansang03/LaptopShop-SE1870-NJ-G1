/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ApplyVoucher", urlPatterns = {"/applyVoucher"})
public class ApplyVoucher extends HttpServlet {

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

        String voucher = request.getParameter("voucher");
        String totalPriceBefore_raw = request.getParameter("totalBefore");
        int totalPriceBefore = Integer.parseInt(totalPriceBefore_raw);

        VoucherDAO vDAO = new VoucherDAO();

        Voucher isVoucher = vDAO.checkVoucher(voucher);

        if (isVoucher == null || isVoucher.getQuantity() == 0) {
            request.setAttribute("error", "Not available ");
        } else {
            if (isVoucher.getMinValue() > totalPriceBefore) {
                request.setAttribute("error", "Your total order value is too low");
            } else if (isVoucher.getMinValue() <= totalPriceBefore) {
                Date curentDate = new Date();
                if (!(isVoucher.getStartDate().compareTo(curentDate) < 0
                        && isVoucher.getEndDate().compareTo(curentDate) > 0)) {
                    request.setAttribute("error", "Your voucher is not included in the time offer");
                } else {
                    int discount = isVoucher.getDiscountPercent();
                    request.setAttribute("success", "You get " + discount + "% discount");
                    request.setAttribute("isVoucher", isVoucher);
                }
            }
        }
        request.setAttribute("voucherInput", voucher);
        request.getRequestDispatcher("loadInfoCheckout").forward(request, response);
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
