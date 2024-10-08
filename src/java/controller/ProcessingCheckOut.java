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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import model.CartItem;
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
            int discount = voucher.getDiscountPercent();

           double totalAfterDiscountDouble =  totalBeforeDiscount - (totalBeforeDiscount * (double)discount/100);
           totalAfterDiscount = (int)totalAfterDiscountDouble;
        }

        OderDAO oDAO = new OderDAO();
        if (paymentMethod.equals("Nhan Hang Thanh Toan")) {
            if (voucherID_raw == null) {
                oDAO.insertOrderOfCODNoVoucher(user.getId(), name, address, phone, dateTimeLocal, totalBeforeDiscount, totalAfterDiscount, paymentMethod);
            } else {
                int discountAmount = (totalAfterDiscount - totalBeforeDiscount);

                int voucherID = Integer.parseInt(voucherID_raw);
                oDAO.insertOrderOfCOD(user.getId(), name, address, phone, dateTimeLocal, voucherID, totalBeforeDiscount, discountAmount, totalAfterDiscount, paymentMethod);
            }

            //insert oderDatail
            int oid = oDAO.getIdOfOrderNewest();
            for (int i = 0; i < cartItem.size(); i++) {
                oDAO.insertOrderDetail(oid, pid[i], qlt[i], unitPrice[i]);
            }
            response.sendRedirect("home");
        } else if (paymentMethod.equals("Payment")) {
            int arrRan[] = new int[5];
            //random code qr
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int ran = random.nextInt(5);
                arrRan[i] = ran;
            }
            String code = "LaptopShop_";
            for (int i = 0; i < arrRan.length; i++) {
                code += arrRan[i];
            }

            if (voucherID_raw == null) {
                oDAO.insertOrderOfPaymentNoVoucher(user.getId(), name, address, phone, dateTimeLocal, totalBeforeDiscount, totalAfterDiscount, paymentMethod, code);
            } else {
                int voucherID = Integer.parseInt(voucherID_raw);
                int discountAmount = (totalAfterDiscount - totalBeforeDiscount);
                oDAO.insertOrderOfPayment(user.getId(), name, address, phone, dateTimeLocal, voucherID, totalBeforeDiscount, discountAmount, totalAfterDiscount, paymentMethod, code);
            }

            //insert oderDatail
            int oid = oDAO.getIdOfOrderNewest();
            for (int i = 0; i < cartItem.size(); i++) {
                oDAO.insertOrderDetail(oid, pid[i], qlt[i], unitPrice[i]);
            }

            request.setAttribute("code", code);
            String totalDiscountStr = String.valueOf(totalAfterDiscount);
            request.setAttribute("paymentData", totalDiscountStr);
            request.getRequestDispatcher("payment").forward(request, response);
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
