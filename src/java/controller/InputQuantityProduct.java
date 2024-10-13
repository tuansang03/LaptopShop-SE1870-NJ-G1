/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAOS;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartItem;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "InputQuantityProduct", urlPatterns = {"/inputQuantityProduct"})
public class InputQuantityProduct extends HttpServlet {

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
        String quantity_raw = request.getParameter("qty");
        String pdtid_raw = request.getParameter("pdtid");
        CartDAOS cDAO = new CartDAOS();
        try {
            int quantity = Integer.parseInt(quantity_raw);
            int pdtid = Integer.parseInt(pdtid_raw);
            CartItem pCart = cDAO.getCartItemByPdtID(pdtid);

            if (!(quantity_raw.isEmpty() || quantity_raw == null)) {
                if (quantity > 0 && quantity <= pCart.getProductdetail().getQuantity()) {
                    cDAO.updateQuantityProduct(quantity, pdtid);
                }else if(quantity <= 0) {
                    cDAO.updateQuantityProduct(pCart.getQuantity(), pdtid);
                }
                else {
                    cDAO.updateQuantityProduct(pCart.getProductdetail().getQuantity(), pdtid);
                }

            } else {
                CartItem cart = cDAO.getCartItemByPdtID(pdtid);
                quantity = cart.getQuantity();
                cDAO.updateQuantityProduct(quantity, pdtid);
            }

        } catch (Exception e) {
        }

        response.sendRedirect("loadProductCart");

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
