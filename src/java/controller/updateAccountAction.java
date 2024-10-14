/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SendMailSSL;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "updateAccountAction", urlPatterns = {"/updateAccountAction"})
public class updateAccountAction extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateAccountAction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateAccountAction at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();

        // Lấy đối tượng User từ session
        User currentUser = (User) session.getAttribute("sale");
        if (currentUser == null) {
            currentUser = (User) session.getAttribute("admin");
        }

        // Kiểm tra xem có người dùng hay không
        if (currentUser == null) {
            request.setAttribute("error", "Bạn cần đăng nhập để cập nhật tài khoản.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String fullName = request.getParameter("fullName");
        String newEmail = request.getParameter("email");
        String currentEmail = currentUser.getEmail(); // Email hiện tại

        // Cập nhật tên đầy đủ
        currentUser.setFullName(fullName);

        // Kiểm tra nếu email mới giống với email hiện tại
        if (newEmail.equals(currentEmail)) {
            UserDAO userDAO = new UserDAO();
            boolean updateSuccess = userDAO.updateUser(currentUser);

            if (updateSuccess) {
                request.setAttribute("message", "Update successful");

            } else {
                request.setAttribute("error", "Update not successful");
            }
            // Chuyển hướng về trang cập nhật tài khoản
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }
        UserDAO udao = new UserDAO();
        if (udao.isMailDuplicate2(newEmail)) {
            request.setAttribute("error", "Email has been registered");
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }
        // Kiểm tra tính hợp lệ của email
        if (!newEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("error", "Email not valid");
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }

        // Nếu email thay đổi, gửi OTP
        String otp = generateOTP();
        session.setAttribute("otp", otp);
        

        // Gửi OTP qua email
        SendMailSSL mailer = new SendMailSSL();
        mailer.sendOTP(newEmail, otp);

        // Chuyển hướng đến trang xác thực OTP
        session.setAttribute("fullName", fullName);
        request.setAttribute("message", "Please check your email to input OTP code");
        session.setAttribute("email", newEmail); // Truyền email sang trang OTP
        request.getRequestDispatcher("otp_verificationAdmin.jsp").forward(request, response);
    }

    private String generateOTP() {
        int otp = (int) (Math.random() * 900000) + 100000; // Tạo số ngẫu nhiên từ 100000 đến 999999
        return String.valueOf(otp);
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
