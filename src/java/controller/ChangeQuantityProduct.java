/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CartDAOS;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ChangeQuantityProduct extends HttpServlet {
   
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
        response.setContentType("text/html;charset=UTF-8");
        String num_raw = request.getParameter("num");
        String cartID_raw = request.getParameter("cid");
        String pdtID_raw = request.getParameter("pdtid");
        CartDAOS cartDAO = new CartDAOS();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cartUser = cartDAO.getCartByUserID(user.getId());

        try {
            int num = Integer.parseInt(num_raw);
            int cartID = Integer.parseInt(cartID_raw);
            int pdtID = Integer.parseInt(pdtID_raw);

            CartItem existProduct = cartDAO.getCartItemByCartIdAndProductId(cartUser.getId(), pdtID);

            if (existProduct != null && num == 1) {
                int newQuantity = existProduct.getQuantity() + num;
                //existProduct.setQuantity(newQuantity);
                cartDAO.updateCartItemQuantity(cartID, pdtID, newQuantity);
            } else if (existProduct != null && num == -1 && existProduct.getQuantity() > 1) {
                int newQuantity = existProduct.getQuantity() + num;
                //existProduct.setQuantity(newQuantity);
                cartDAO.updateCartItemQuantity(cartID, pdtID, newQuantity);
            } else if (existProduct != null && existProduct.getQuantity() == 1 && num == -1) {
                cartDAO.deleteCartItem(cartID, pdtID); // Delete product if quantity = 0
            }
        } catch (Exception e) {
        }

        response.sendRedirect("loadProductCart");
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
        processRequest(request, response);
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
