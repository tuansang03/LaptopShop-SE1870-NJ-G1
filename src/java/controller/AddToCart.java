/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAOS;
import dal.ImageDAOS;
import dal.ProductDAOS;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.Image;
import model.ProductDetail;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/addtocart"})
public class AddToCart extends HttpServlet {

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
        HttpSession session = request.getSession();
        String pid_raw = request.getParameter("pid");
        String colorid_raw = request.getParameter("colorid");
        String confid_raw = request.getParameter("confid");
        User user = (User) session.getAttribute("user");
        ProductDAOS pDAO = new ProductDAOS();
        CartDAOS cartDAO = new CartDAOS();
        ImageDAOS iDAO = new ImageDAOS();

        int pid = Integer.parseInt(pid_raw);
        int colorid = Integer.parseInt(colorid_raw);
        int confid = Integer.parseInt(confid_raw);
        ProductDetail pDetail = pDAO.getProductDetailByProductID(pid, colorid, confid);
        Cart cartUser = cartDAO.getCartByUserID(user.getId());

        if (cartUser == null) {
            cartDAO.addToCart(user.getId());
            cartUser = cartDAO.getCartByUserID(user.getId());
        }
        CartItem existProduct = cartDAO.getCartItemByCartIdAndProductId(cartUser.getId(), pDetail.getId());

        if (existProduct != null) {
            int newQuantity = existProduct.getQuantity() + 1;
            //existProduct.setQuantity(newQuantity);
            cartDAO.updateCartItemQuantity(existProduct.getCart().getId(), pDetail.getId(), newQuantity);
        } else {
            cartDAO.addToCartItem(cartUser.getId(), pDetail.getId(), 1);
        }

        //List<CartItem> listCartItem = cartDAO.getAllProductOfCartItem(cartUser.getId());

        Image image = iDAO.getOneImageByProductDetailID(pDetail.getId());

        request.setAttribute("image", image);
        //request.setAttribute("listCartItem", listCartItem);
        request.getRequestDispatcher("index.jsp").forward(request, response);

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
