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
import static model.PasswordUtil.hashPassword;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "changePassword", urlPatterns = {"/changePassword"})
public class changePassword extends HttpServlet {

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
            out.println("<title>Servlet changePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changePassword at " + request.getContextPath() + "</h1>");
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
        User currentUser = (User) session.getAttribute("sale");

// Kiểm tra xem có phải admin không
        if (currentUser == null) {
            currentUser = (User) session.getAttribute("admin");
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String hashedPassword = hashPassword(currentPassword);

        
        //Kiểm tra mật khẩu mới và hiện tại bằng nhau
        if(newPassword.equals(currentPassword)){
            request.setAttribute("error", "Can't change match password");
            request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("confirmPassword", confirmPassword);
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }
        
// Kiểm tra mật khẩu hiện tại
        UserDAO userDAO = new UserDAO();
        if (!userDAO.checkPassword(currentUser.getUserName(), hashedPassword)) {
            request.setAttribute("error", "Current password not correct");
            request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("confirmPassword", confirmPassword);
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }

// Kiểm tra mật khẩu mới phải lớn hơn 8 ký tự
        if (newPassword.length() < 8) {
            request.setAttribute("error", "New password must be at least 8 characters long");
             request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("confirmPassword", confirmPassword);
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }

// Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirm password do not match");
             request.setAttribute("currentPassword", currentPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("confirmPassword", confirmPassword);
            request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);
            return;
        }

// Băm mật khẩu mới
        String hashedNewPassword = hashPassword(newPassword);

// Cập nhật mật khẩu mới
        userDAO.updatePassword(currentUser.getUserName(), hashedNewPassword);

// Thông báo thành công
        request.setAttribute("message", "Password change successful");
        request.getRequestDispatcher("updateAccountPage.jsp").forward(request, response);

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
