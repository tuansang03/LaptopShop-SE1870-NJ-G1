/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import model.SendMailSSL;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ResendOTPStaff", urlPatterns={"/ResendOTPStaff"})
public class ResendOTPStaff extends HttpServlet {
   
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
            out.println("<title>Servlet ResendOTPStaff</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendOTPStaff at " + request.getContextPath () + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session=request.getSession();
        User currentUser = (User) session.getAttribute("sale");
            if (currentUser == null) {
                currentUser = (User) session.getAttribute("admin");
            }
           String email = (String) session.getAttribute("email");
           if (email == null) {
            request.setAttribute("error", "Session expired or email not found.");
            request.getRequestDispatcher("otp_verificationAdmin.jsp").forward(request, response);
            return;
        }
           try {
            // Tạo OTP mới
            
            String newOTP = generateOTP();
            // Gửi OTP mới qua email
            SendMailSSL mail=new SendMailSSL();
            mail.sendOTP(email, newOTP);
            
            // Lưu OTP vào session
            session.removeAttribute("otp");
            session.setAttribute("otp", newOTP);
            
            // Thông báo thành công
            request.setAttribute("message", "A new OTP has been sent to your email.");
            request.getRequestDispatcher("otp_verificationAdmin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to send OTP. Please try again.");
        }
    } 
private String generateOTP() {
        // Tạo OTP 6 chữ số
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
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
