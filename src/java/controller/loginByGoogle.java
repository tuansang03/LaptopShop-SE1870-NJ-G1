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

        try {
            // Lấy access token từ code
            String accessToken = gg.getToken(code);

            // Lấy thông tin tài khoản Google của người dùng
            GoogleAccount acc = gg.getUserInfo(accessToken);

            if (acc != null) {
                // Lưu thông tin người dùng vào session
                HttpSession session = request.getSession();
                String googleEmail = acc.getEmail();  // Email từ Google
                String googleName = acc.getName();    // Tên người dùng từ Google

                // Kiểm tra xem người dùng đã có tài khoản với email này trong hệ thống chưa
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getUserByEmail(googleEmail);

                if (user != null) {
                   if (user.getStatus().equalsIgnoreCase("ban")) {
                session.setAttribute("ban", user);
                response.sendRedirect("ban.jsp");
                return;
            }
                    if (user.getRole().getId() == 3) {
                session.setAttribute("user", user);
                response.sendRedirect("home");
            } else if (user.getRole().getId() == 2) {
                session.setAttribute("sale", user);
                response.sendRedirect("SaleStatisticController2?service=listall");
            } else if (user.getRole().getId() == 1) {
                session.setAttribute("admin", user);
                response.sendRedirect("admindashboard.jsp");
            }
                } else {
                    // Nếu người dùng chưa có tài khoản, tự động tạo tài khoản mới
                    User newUser = new User();
                    
                    newUser.setEmail(googleEmail);
                    newUser.setFullName(googleName);
                    String pass = PasswordUtil.hashPassword(PasswordUtil.generateRandomPassword());
                    newUser.setPassword(pass);  // Nếu không cần mật khẩu khi đăng nhập bằng Google
                    newUser.setUserName(acc.getName());
                    Role role = new Role();
                    role.setId(3);
                    newUser.setRole(role);
                    newUser.setStatus("active");
                    
                    // Gọi DAO để lưu người dùng mới vào cơ sở dữ liệu
                   int id = userDAO.insertUser2(newUser);
                     newUser.setId(id);
                    // Đăng nhập tự động người dùng vừa tạo
                    session.setAttribute("user", newUser);
                     response.sendRedirect("home");
                }
            
                // Chuyển hướng người dùng đến trang chính
               
            } else {
                // Nếu không nhận được thông tin từ Google, chuyển hướng đến trang lỗi
                response.sendRedirect("errorpage.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi xảy ra, chuyển hướng đến trang lỗi
            response.sendRedirect("errorpage.jsp");
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
