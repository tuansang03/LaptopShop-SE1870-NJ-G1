/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAOS;
import dal.ImageDAOS;
import dal.OderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Image;
import model.Order;
import model.User;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoadInfoCheckOut", urlPatterns = {"/loadInfoCheckout"})
public class LoadInfoCheckOut extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDAOS cDAO = new CartDAOS();

        int cid = cDAO.getCartByUserID(user.getId()).getId();
        List<CartItem> cartItem = cDAO.getProductSelectd(cid);

        if (cartItem.isEmpty()) {
            request.setAttribute("error", "Please select at least 1 product");
            request.getRequestDispatcher("loadProductCart").forward(request, response);
            return;
        }

        String name = user.getFullName();
        String email = user.getEmail();

        ImageDAOS iDAO = new ImageDAOS();
        List<Image> listImages = new ArrayList<>();

        int totalPrice = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            totalPrice += cartItem.get(i).getQuantity() * cartItem.get(i).getProductdetail().getPrice();
            int productDetailId = cartItem.get(i).getProductdetail().getId();
            Image image = iDAO.getOneImageByProductDetailID(productDetailId);
            listImages.add(image); // Thêm hình ảnh vào danh sách
        }

        Voucher voucher = (Voucher) request.getAttribute("isVoucher");
        if (voucher != null) {
            request.setAttribute("voucher", voucher);
        }

        OderDAO oDAO = new OderDAO();
        Order order = oDAO.getOneOrderNewest(user.getId());

        if (order != null) {
            String phone = order.getPhone();
            String address = order.getAddress();
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
        }

        request.setAttribute("cartItem", cartItem);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("listImages", listImages);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
