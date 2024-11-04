/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAOS;
import dal.OderDAO;
import dal.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import model.CartItem;
import model.SendMailSSL;
import model.SendMailSSL;
import model.User;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProcessingCheckOut", urlPatterns = {"/processingCheckOut"})
public class ProcessingCheckOut extends HttpServlet {

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
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String totalPriceBeforeDiscount_raw = request.getParameter("totalPriceBeforeDiscount");
        int totalBeforeDiscount = Integer.parseInt(totalPriceBeforeDiscount_raw);
        String message = request.getParameter("message");
        String paymentMethod = request.getParameter("selector");

        CartDAOS cDAO = new CartDAOS();
        int cid = cDAO.getCartByUserID(user.getId()).getId();
        List<CartItem> cartItem = cDAO.getProductSelectd(cid);
        int pid[] = new int[cartItem.size()];
        int qlt[] = new int[cartItem.size()];
        int unitPrice[] = new int[cartItem.size()];
        for (int i = 0; i < cartItem.size(); i++) {
            pid[i] = cartItem.get(i).getProductdetail().getId();
            qlt[i] = cartItem.get(i).getQuantity();
            unitPrice[i] = cartItem.get(i).getProductdetail().getPrice();
        }

        if (paymentMethod.equals("nhanhang")) {
            paymentMethod = "Nhan Hang Thanh Toan";
        } else {
            paymentMethod = "Payment";
        }

        LocalDateTime dateTimeLocal = LocalDateTime.now();
        int totalAfterDiscount = 0;

        String voucherID_raw = request.getParameter("voucherID");
        if (voucherID_raw != null) {
            VoucherDAO vDAO = new VoucherDAO();
            Voucher voucher = vDAO.getVoucherByID(Integer.parseInt(voucherID_raw));

            //xử lý giá sau khi app dụng voucher
            int discount = voucher.getDiscountPercent();
            double totalAfterDiscountDouble = totalBeforeDiscount - (totalBeforeDiscount * (double) discount / 100);
            totalAfterDiscount = (int) totalAfterDiscountDouble;

            //giảm số lượng của voucher khi được sử dụng
            int quantityVoucher = voucher.getQuantity();
            vDAO.updateQuantityVoucher((quantityVoucher - 1), Integer.parseInt(voucherID_raw));

            //tăng số lượng voucher đã sử dụng
            int usedVoucher = voucher.getUsedQuantity();
            vDAO.updateUsedQuantityVoucher((usedVoucher + 1), Integer.parseInt(voucherID_raw));

        }

        OderDAO oDAO = new OderDAO();
       if (paymentMethod.equals("Nhan Hang Thanh Toan")) {
            int sallerID = oDAO.getSallerMinOrder();
            if (voucherID_raw == null) {
                oDAO.insertOrderOfCODNoVoucher(user.getId(), name, address, phone, dateTimeLocal, totalBeforeDiscount, (totalAfterDiscount == 0) ? totalBeforeDiscount : totalBeforeDiscount, paymentMethod, message == null ? null : message, sallerID);
                cDAO.deleteProductAfterCheckOut(cid);
            } else {
                int discountAmount = (totalBeforeDiscount - totalAfterDiscount);
                int voucherID = Integer.parseInt(voucherID_raw);

                oDAO.insertOrderOfCOD(user.getId(), name, address, phone, dateTimeLocal, voucherID, totalBeforeDiscount, discountAmount, totalAfterDiscount, paymentMethod, message == null ? null : message, sallerID);
                cDAO.deleteProductAfterCheckOut(cid);
            }

            //insert oderDetail
            int oid = oDAO.getIdOfOrderNewest();
            for (int i = 0; i < cartItem.size(); i++) {
                oDAO.insertOrderDetail(oid, pid[i], qlt[i], unitPrice[i]);
            }
            SendMailSSL mailSender = new SendMailSSL();
            mailSender.sendOrderConfirmation(email, oid);
            response.sendRedirect("orderSuccess.jsp");
        } else if (paymentMethod.equals("Payment")) {

            if (totalAfterDiscount == 0) {
                totalAfterDiscount = totalBeforeDiscount;
            }
            String totalDiscountStr = String.valueOf(totalAfterDiscount);

            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.setAttribute("totalPriceBeforeDiscount_raw", totalPriceBeforeDiscount_raw);
            request.setAttribute("message", message);
            request.setAttribute("voucherID_raw", voucherID_raw);
            request.setAttribute("paymentData", totalDiscountStr);
            request.getRequestDispatcher("payment").forward(request, response);
            //response.sendRedirect("payment");
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
