/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static model.PasswordUtil.hashPassword;
import static model.PasswordUtil.isValidPassword;
import model.SendMailSSL;
import model.User;

/**
 *
 * @author ADMIN
 */
public class register extends HttpServlet {

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
            out.println("<title>Servlet register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String firstName = request.getParameter("firstName");
        String lastName= request.getParameter("lastName");
        String fullname=firstName +" "+lastName;
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");

        User u = new User(name, password, fullname, email);
        UserDAO udao = new UserDAO();
        if (udao.isMailDuplicate(u)) {
            request.setAttribute("error", "Email has been registered");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (udao.isUserDuplicate(u)) {
            request.setAttribute("error", "Username has been registered");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("error", "Email not valid");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!isValidPassword(password)){
            request.setAttribute("error", "Password must length 8 character");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        String hashedPassword = hashPassword(password);
        u.setPassword(hashedPassword);
        String otp = generateOTP();

    // Save OTP to session
    HttpSession session = request.getSession();
    session.setAttribute("otp", otp);
    session.setAttribute("uRegister", u);
    // Gửi OTP qua email
    SendMailSSL mailer = new SendMailSSL();
    mailer.sendOTP(email, otp);

    // Chuyển hướng đến trang xác thực OTP
    request.setAttribute("message", "Please check your mail and input your OTP");
    request.setAttribute("email", email);  // Truyền email sang trang OTP
    request.getRequestDispatcher("otp_verification.jsp").forward(request, response);
    }
public String generateOTP() {
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
