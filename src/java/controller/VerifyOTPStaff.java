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
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "VerifyOTPStaff", urlPatterns = {"/VerifyOTPStaff"})
public class VerifyOTPStaff extends HttpServlet {

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
            out.println("<title>Servlet VerifyOTPStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOTPStaff at " + request.getContextPath() + "</h1>");
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
        String otpInput = request.getParameter("otp");
        HttpSession session = request.getSession();
        String otpStored = (String) session.getAttribute("otp");

        // Kiểm tra OTP
        if (otpStored != null && otpStored.equals(otpInput)) {
            // OTP hợp lệ, tiến hành cập nhật thông tin
            String fullName = (String) session.getAttribute("fullName");
            String email = (String) session.getAttribute("email");

            User currentUser = (User) session.getAttribute("sale");
            if (currentUser == null) {
                currentUser = (User) session.getAttribute("admin");
            }
            UserDAO userDAO = new UserDAO();

            // Cập nhật thông tin người dùng
            currentUser.setFullName(fullName);
            currentUser.setEmail(email);    
            userDAO.updateUser(currentUser); // Giả sử bạn đã có phương thức updateUser trong UserDAO

            session.removeAttribute("otp"); // Xóa OTP khỏi session
            session.removeAttribute("fullName");
            session.removeAttribute("email");
            request.setAttribute("message", "Account updated successfully!");
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
        } else {
            // OTP không hợp lệ, trở về trang cập nhật tài khoản với thông báo lỗi
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("otp_verificationAdmin.jsp").forward(request, response);
        }
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
