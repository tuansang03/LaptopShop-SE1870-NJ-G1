/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OderDAO;
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
 * @author LocPham
 */
@WebServlet(name = "VerifyOTPServlet1", urlPatterns = {"/VerifyOTPServlet1"})
public class VerifyOTPServlet1 extends HttpServlet {

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
        String oid_raw = request.getParameter("oid");
        String op = request.getParameter("op");

        int oid = Integer.parseInt(oid_raw);
        OderDAO oDAO = new OderDAO();

        oDAO.deleteOrderDetail(oid);
        oDAO.deleteOrder(oid);

        if (op.equals("failed")) {
            String action = "failed";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
        } else if (op.equals("rejected")) {
            String action = "rejected";
            request.setAttribute("action", action);
            response.sendRedirect("selectOrderbyStatus?action=" + action);
        }
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

        if (otpStored != null && otpStored.equals(otpInput)) {
            // OTP hợp lệ, chuyển hướng đến trang tiếp theo
            User u = (User) session.getAttribute("uRegister");
            UserDAO dao = new UserDAO();
            dao.insertUserStaff(u);
            session.removeAttribute("uRegister");
            session.removeAttribute("otp");
            request.setAttribute("success", "Register successfully!");
        request.getRequestDispatcher("otp_verification1.jsp").forward(request, response);
        } else {
            // OTP không hợp lệ, trả về trang OTP với thông báo lỗi
            request.setAttribute("error", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("otp_verification1.jsp").forward(request, response);
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
