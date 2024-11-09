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
import java.security.SecureRandom;
import model.GoogleAccount;
import model.GoogleLogin;
import model.PasswordUtil;
import static model.PasswordUtil.generateRandomPassword;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet("/google-callback")
public class loginByGoogle extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginByGoogle</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginByGoogle at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String code = request.getParameter("code");
        GoogleLogin gg = new GoogleLogin();

// Lấy access token từ code
        String accessToken = gg.getToken(code);
        HttpSession session = request.getSession();

// Lấy thông tin tài khoản Google của người dùng
        GoogleAccount acc = gg.getUserInfo(accessToken);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(acc.getEmail());
        String googleEmail = acc.getEmail();
        String googleName = acc.getName();

        if (user != null) {
            // Kiểm tra trạng thái của người dùng
            if (user.getStatus().equalsIgnoreCase("ban")) {
                session.setAttribute("ban", user);
                response.sendRedirect("ban.jsp");
                return;
            }

            // Kiểm tra role của người dùng và chuyển hướng
            switch (user.getRole().getId()) {
                case 1: // Admin
                    session.setAttribute("admin", user);
                    response.sendRedirect("admindashboard.jsp");
                    break;
                case 2: // Sale
                    session.setAttribute("sale", user);
                    response.sendRedirect("SaleStatisticController2?service=listall");
                    break;
                case 3: // User
                    session.setAttribute("user", user);
                    response.sendRedirect("home");
                    break;
            }
        } else {
            // Tạo tài khoản mới nếu người dùng chưa tồn tại trong hệ thống
            User newUser = new User();
            newUser.setEmail(googleEmail);
            newUser.setFullName(googleName);

            // Tạo mật khẩu ngẫu nhiên nếu cần thiết
            String pass = PasswordUtil.hashPassword(PasswordUtil.generateRandomPassword());
            newUser.setPassword(pass);
            newUser.setUserName(googleName);

            // Đặt vai trò và trạng thái mặc định cho người dùng mới
            Role role = new Role();
            role.setId(3);  // Mặc định là User
            newUser.setRole(role);
            newUser.setStatus("active");

            // Lưu người dùng mới vào cơ sở dữ liệu
            int id = userDAO.insertUser2(newUser);
            newUser.setId(id);

            // Đăng nhập tự động cho người dùng mới
            session.setAttribute("user", newUser);
            response.sendRedirect("home");
        }

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
