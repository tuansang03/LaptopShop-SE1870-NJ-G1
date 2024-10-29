/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAOS;
import dal.OderDAO;
import dal.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CartItem;
import model.SendMailSSL;
import model.User;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PaymentResult", urlPatterns = {"/PaymentResult"})
public class PaymentResult extends HttpServlet {

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
        String vnp_TxnRef = request.getParameter("vnp_TxnRef"); // Mã giao dịch
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode"); // Mã phản hồi của giao dịch
        String vnp_Amount = request.getParameter("vnp_Amount"); // Số tiền giao dịch
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        String vnp_PayDate = request.getParameter("vnp_PayDate");
        String vnp_BankCode = request.getParameter("vnp_BankCode");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        OderDAO oDAO = new OderDAO();
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

        String paymentStatus = "Fail";
        if ("00".equals(vnp_ResponseCode)) { // "00" là mã thành công
            paymentStatus = "Success";
        }

        if (vnp_OrderInfo != null) {
            // Phân tách các phần trong vnp_OrderInfo
            String[] parts = vnp_OrderInfo.split("\\|");
            Map<String, String> orderDetails = new HashMap<>();

            // Lặp qua từng phần để tách key và value
            for (String part : parts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    // Giải mã và thêm vào orderDetails
                    orderDetails.put(URLDecoder.decode(keyValue[0], "UTF-8"),
                            URLDecoder.decode(keyValue[1], "UTF-8"));
                }
            }

            // Truy xuất thông tin từ orderDetails
            String name = orderDetails.get("name");
            String address = orderDetails.get("address");
            String phone = orderDetails.get("phone");
            String totalPriceBeforeDiscount_raw = orderDetails.get("totalPrice");
            String message = orderDetails.get("message");
            String voucherID_raw = orderDetails.get("voucherID");
            String email = orderDetails.get("email");
            int totalBeforeDiscount = Integer.parseInt(totalPriceBeforeDiscount_raw);

            int totalAfterDiscount = 0;

            // String voucherID_raw = request.getParameter("voucherID");
            if ("00".equals(vnp_ResponseCode)) { // "00" là mã thành công
                if (voucherID_raw != null) {
                    VoucherDAO vDAO = new VoucherDAO();
                    Voucher voucher = vDAO.getVoucherByID(Integer.parseInt(voucherID_raw));

                    //xử lý giá sau khi app dụng voucher
                    int discount = voucher.getDiscountPercent();
                    double totalAfterDiscountDouble = totalBeforeDiscount - (totalBeforeDiscount * (double) discount / 100);
                    totalAfterDiscount = (int) totalAfterDiscountDouble;

//                    //giảm số lượng của voucher khi được sử dụng
//                    int quantityVoucher = voucher.getQuantity();
//                    vDAO.updateQuantityVoucher((quantityVoucher - 1), Integer.parseInt(voucherID_raw));
//
//                    //tăng số lượng voucher đã sử dụng
//                    int usedVoucher = voucher.getUsedQuantity();
//                    vDAO.updateUsedQuantityVoucher((usedVoucher + 1), Integer.parseInt(voucherID_raw));
                }
                LocalDateTime dateTimeLocal = LocalDateTime.now();
                int sallerID = oDAO.getSallerMinOrder();
                if (voucherID_raw == null) {
                    oDAO.insertOrderOfPaymentNoVoucher(user.getId(), name, address, phone, dateTimeLocal, totalBeforeDiscount, (totalAfterDiscount == 0) ? totalBeforeDiscount : totalBeforeDiscount, "Payment", paymentStatus, vnp_TxnRef, message == null ? null : message, sallerID);
                    cDAO.deleteProductAfterCheckOut(cid);
                } else {
                    int voucherID = Integer.parseInt(voucherID_raw);
                    int discountAmount = (totalBeforeDiscount - totalAfterDiscount);

                    oDAO.insertOrderOfPayment(user.getId(), name, address, phone, dateTimeLocal, voucherID, totalBeforeDiscount, discountAmount, totalAfterDiscount, "Payment", paymentStatus, vnp_TxnRef, message == null ? null : message, sallerID);
                    cDAO.deleteProductAfterCheckOut(cid);
                }

                //insert oderDetail
                int oid = oDAO.getIdOfOrderNewest();
                for (int i = 0; i < cartItem.size(); i++) {
                    oDAO.insertOrderDetail(oid, pid[i], qlt[i], unitPrice[i]);
                }
                SendMailSSL mailSender = new SendMailSSL();
                mailSender.sendOrderConfirmation(email, oid);
            }

            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
        }

        request.setAttribute("vnp_TxnRef", vnp_TxnRef);
        request.setAttribute("vnp_Amount", vnp_Amount);
        request.setAttribute("vnp_PayDate", vnp_PayDate);
        request.setAttribute("vnp_BankCode", vnp_BankCode);
        request.setAttribute("vnp_ResponseCode", vnp_ResponseCode);

        request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
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
